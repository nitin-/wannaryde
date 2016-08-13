package com.example.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.web.model.request.UserRegistration;
import com.example.web.model.response.Response;

public interface UserService {

	public Response registerUser(UserRegistration userData);
	
	public Response userRegistration(UserRegistration userDetail);
	
	public Response updateProfileImage(MultipartFile userImage,Integer userId);
	
	public Response updateCarImage(MultipartFile carImage, Integer userId);
	
	public Response isUserAvailable(String emailId);
	
	public Response verifyUserOtp(String otp,String userId);
	
}
