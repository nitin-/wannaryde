package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.PushService;
import com.example.service.UserService;
import com.example.web.model.response.Response;

@RestController
@RequestMapping(value = "/master")
public class MasterDataController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PushService pushService;
	
	@RequestMapping(value="/user/available",method=RequestMethod.GET)
	public Response isUserExist(@RequestParam(value="emailId",required=true)String emailId){
		return userService.isUserAvailable(emailId);
	}
	
	/*@RequestMapping(value="/android/push")
	public Response pushNotificationForAndroid(HttpServletRequest request,HttpServletResponse response){
		String collapseKey = request.getParameter("CollapseKey");
		String userMessage= request.getParameter("Message");
		return pushService.pushToAndroid(collapseKey,userMessage);
	}*/

}
