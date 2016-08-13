package com.example.service.impl;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dal.dao.UserDao;
import com.example.dal.entity.User;
import com.example.service.RydeService;
import com.example.service.UserService;
import com.example.service.util.LatLong;
import com.example.service.util.Util;
import com.example.web.component.S3FileStorage;
import com.example.web.model.FileData;
import com.example.web.model.request.RydeCreateRequest;
import com.example.web.model.request.UserRegistration;
import com.example.web.model.response.Response;
import com.example.web.util.Constants;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	S3FileStorage storage;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	Environment env;
	
	@Autowired
	RydeService rydeRyderService;
	
	@Override
	public Response registerUser(UserRegistration userData) {
		    Response responseD = Util.getResponseInstance();
		    /*User userEntity = mapper.map(userData, User.class);
			try{
			MultipartFile carImage = userData.getCarImage();
			if(null != carImage && "default.jpg".equalsIgnoreCase(carImage.getOriginalFilename())){
			 String eTag = uploadFileToS3(carImage.getBytes(), userData.getMobile()+"-car.jpg", "/images", carImage.getContentType());
			  if(null != eTag){
				  String carImagePath = env.getProperty(Constants.AWS_S3_BUCKET_URL)+"/"+env.getProperty(Constants.AWS_S3_BUCKET)+"/images/"+userData.getMobile()+"-car.jpg";
				  userEntity.setCarImagePath(carImagePath);
			  }
				  
			}
			}catch(Exception e){
				e.printStackTrace();
				responseD.setMessage("Error uploading car Image \n Cause: "+e.getMessage());
			}
			
			try{
			MultipartFile userImage = userData.getUserImage();
			if(null !=userImage && "default.jpg".equalsIgnoreCase(userImage.getOriginalFilename())){
				 String eTag = uploadFileToS3(userImage.getBytes(), userData.getMobile()+"-user-profile.jpg", "/images", userImage.getContentType());
				  if(null != eTag){
					  String userImagePath = env.getProperty(Constants.AWS_S3_BUCKET_URL) +"/"+env.getProperty(Constants.AWS_S3_BUCKET)+"/images/"+userData.getMobile()+"-user-profile.jpg";
					  userEntity.setUserImagePath(userImagePath);
				  }
					  
			}
			}catch(Exception e){
				e.printStackTrace();
				responseD.setMessage(responseD.getMessage() +", Error uploading user Image \n Cause: "+e.getMessage());
			}
			try{
				userDao.save(userEntity);
				UserDetail userDto = mapper.map(userEntity, UserDetail.class);
				responseD.setStatus(200);
				responseD.setMessage(responseD.getMessage() +" successfully inserted dataa in DB");
				responseD.setData(userDto);
			}catch(Exception e){
				e.printStackTrace();
				responseD.setStatus(500);
				responseD.setMessage(responseD.getMessage() +" Error registering user \n Cause: "+e.getMessage());
			}
	*/	return responseD;
	}
	
	private String uploadFileToS3(byte[] byteArr, String fileName, String folder, String contentType){
		FileData file = new FileData(fileName, byteArr, folder, contentType);
		String eTag = storage.storeFile(file);
		//logger.info(fileName + " uploaded successfully to S3");
		return eTag;
	}

	@Override
	public Response userRegistration(UserRegistration userDetail) {
		Response responseD = Util.getResponseInstance();
		try{
			User userEntity = mapper.map(userDetail, User.class);
			userEntity.setPassword(new BCryptPasswordEncoder().encode(userDetail.getPassword()));
			String otp = Util.getOTP();
			userEntity.setOtp(otp);
			if(null == userDetail.getUserType())
				userEntity.setUserType(Constants.UserType.RYDEE.toString());
			userEntity.setStartTime(new Date());
			User userFromDb = userDao.save(userEntity);
			emailService.sendMail(userDetail.getEmail(), env.getProperty("mail.user"), null, null, "OTP for wizkid App","Here is your verification code "+otp, false);
			//copy relevant data to rydeRyderrequest.
			RydeCreateRequest rC = new RydeCreateRequest();
			copyToRydeRyder(userEntity,rC,responseD);
			if(responseD.getStatus() == 200)
				rydeRyderService.insertRydeRyderRequest(rC);
			filterResponseAfterRegistration(userFromDb);
			responseD.setData(userFromDb);
			responseD.setMessage("User Sucessfully Registered.");
			responseD.setStatus(200);
			
			
		}catch(Exception e){
			e.printStackTrace();
			responseD.setData(null);
			responseD.setMessage(e.getCause().toString());
			responseD.setStatus(500);
			//userDao.flush();
		}
		return responseD;
	}

	@Override
	public Response updateProfileImage(MultipartFile userImage, Integer userId) {
		Response responseD = Util.getResponseInstance();
		try{
		User user = userDao.findByID(userId);
		if(null !=userImage && "default.jpg".equalsIgnoreCase(userImage.getOriginalFilename())){
			 String eTag = uploadFileToS3(userImage.getBytes(), user.getMobile()+"-user-profile.jpg", "/images", userImage.getContentType());
			  if(null != eTag){
				  String userImagePath = env.getProperty(Constants.AWS_S3_BUCKET_URL) +"/"+env.getProperty(Constants.AWS_S3_BUCKET)+"/images/"+user.getMobile()+"-user-profile.jpg";
				  user.setUserImagePath(userImagePath);
				  responseD.setData(userDao.save(user));
				  responseD.setMessage("Profile Picture Updated Successfully.");
				  responseD.setStatus(200);
			  }
		}
		}catch(Exception e){
			e.printStackTrace();
			responseD.setData(null);
			responseD.setMessage(e.getCause().toString());
			responseD.setStatus(500);
		}
		return responseD;
	}

	@Override
	public Response updateCarImage(MultipartFile carImage, Integer userId) {
		Response responseD = Util.getResponseInstance();
		try{
		User user = userDao.findByID(userId);
		if(null !=carImage && "default.jpg".equalsIgnoreCase(carImage.getOriginalFilename())){
			 String eTag = uploadFileToS3(carImage.getBytes(), user.getMobile()+"-car.jpg", "/images", carImage.getContentType());
			  if(null != eTag){
				  String carImagePath = env.getProperty(Constants.AWS_S3_BUCKET_URL) +"/"+env.getProperty(Constants.AWS_S3_BUCKET)+"/images/"+user.getMobile()+"-car.jpg";
				  user.setUserImagePath(carImagePath);
				  responseD.setData(userDao.save(user));
				  responseD.setMessage("Car Image Updated Successfully.");
				  responseD.setStatus(200);
			  }
		}
		}catch(Exception e){
			e.printStackTrace();
			responseD.setData(null);
			responseD.setMessage("Error in Uploading Car Image");
			responseD.setStatus(500);
		}
		return responseD;
	}

	@Override
	public Response isUserAvailable(String emailId) {
		Response responseD = Util.getResponseInstance();
		try{
			User u = userDao.findByEmail(emailId);
			if(null != u){
				responseD.setData(true);
				responseD.setMessage("User Already exist");
				responseD.setStatus(200);
			}else{
				responseD.setData(false);
				responseD.setMessage("User does'nt exist");
				responseD.setStatus(200);
			}
		}catch(Exception e){
			e.printStackTrace();
			responseD.setData(false);
			responseD.setMessage("Server Error");
			responseD.setStatus(500);
		}
		return responseD;
	}

	@Override
	public Response verifyUserOtp(String otp,String emailId) {
		Response responseD = Util.getResponseInstance();
		try{
			User user = userDao.findByEmail(emailId);
			if(null == user){
				responseD.setData(false);
				responseD.setMessage("User does'nt exist or incorrect userId");
				responseD.setStatus(200);
				return responseD;
			}
			if(user.getOtp().equalsIgnoreCase(otp)){
				user.setOtpVerified(true);
				userDao.update(user);
				responseD.setData(true);
				responseD.setMessage("User Verified ");
				responseD.setStatus(200);
			}else{
				responseD.setData(false);
				responseD.setMessage("In correct Otp ");
				responseD.setStatus(200);
			}
		}catch(Exception e){
			e.printStackTrace();
			responseD.setData(null);
			responseD.setMessage(e.getCause().toString());
			responseD.setStatus(500);
		}
		return responseD;
	}
	
	
	private void copyToRydeRyder(User userEntity,RydeCreateRequest rC,Response responseD){
		try{
			rC.setDestination(userEntity.getDefaultOfficeLandmark());
			rC.setSource(userEntity.getDefaultHomeLandmark());
			LatLong LL = Util.getLatLong(userEntity.getSourceLandMarkLatLong(), userEntity.getDestinationLandMarkLatLong());
				rC.setSourceLatitude(LL.getSourceLat());
				rC.setSourceLongitude(LL.getSourceLong());
				rC.setDestinationLatitude(LL.getDestLat());
				rC.setDestinationLongitude(LL.getDestLong());
				rC.setStartTime(Util.parseDateToString(userEntity.getStartTime()));
				rC.setDestination(userEntity.getDefaultOfficeAddress());
				rC.setSource(userEntity.getDefaultHomeAddress());
				rC.setUserId(userEntity.getId());
				rC.setUserType(userEntity.getUserType());
			responseD.setStatus(200);

		}catch(Exception e){
			e.printStackTrace();
			responseD.setData(null);
			responseD.setMessage(e.getCause().toString());
			responseD.setStatus(500);

		}
	}
	
	private void filterResponseAfterRegistration(User user){
		
	}

}
