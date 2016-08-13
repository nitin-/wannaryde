package com.example.web.controller;

import java.io.IOException;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.PushService;
import com.example.service.UserService;
import com.example.web.model.request.UserRegistration;
import com.example.web.model.response.Response;
import com.example.web.model.response.UserDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	PushService pushService;
	
	//Not In Use
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Response registerUser(@RequestParam(value="userDetail",required=false) String userDetail,@RequestParam(value="carImage",required=false) MultipartFile carImage,@RequestParam(value="userImage",required=false) MultipartFile userImage) throws JsonParseException, JsonMappingException, IOException{
		UserRegistration userJson = new ObjectMapper().readValue(userDetail, UserRegistration.class);
		UserRegistration user = formObject(userJson,carImage,userImage);
		return userService.registerUser(user);
	}
	
	@RequestMapping(value="/update/mobile/")
	public UserDetail updateUserMobile(@RequestParam(value="mobile_number") String mobileNum){
		return null;
		
	}
	
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public Response registerUser(@RequestBody UserRegistration userDetail) throws JsonParseException, JsonMappingException, IOException{
		return userService.userRegistration(userDetail);
	}
	
	@RequestMapping(value="/update/email")
	public UserDetail updateUserEmail(@RequestParam(value="emailId") String email){
		return null;
	}
	
	@RequestMapping(value="/update/image/profile",method=RequestMethod.POST)
	public Response changeProfilePic(@RequestParam("userImage") MultipartFile userImage,@RequestParam("userId") Integer userId){
		return userService.updateProfileImage(userImage, userId);
	}
	
	@RequestMapping(value="/update/image/car",method=RequestMethod.POST)
	public Response changeCarPic(@RequestParam("carImage") MultipartFile carImage,@RequestParam("userId") Integer userId){
		return userService.updateCarImage(carImage, userId);
	}
	
	private UserRegistration formObject(UserRegistration userDetail,MultipartFile carImage,MultipartFile userImage){
		userDetail.setCarImage(carImage);
		userDetail.setUserImage(userImage);
		return userDetail;
	}
	
	@RequestMapping(value="/verify")
	public Response verifyUser(@RequestParam("otp") String otp,@RequestParam("email")String emailId){
		return userService.verifyUserOtp(otp, emailId);
	}
}
