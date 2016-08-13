package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.RydeService;
import com.example.web.model.request.ActionOnCard;
import com.example.web.model.request.RydeCreateRequest;
import com.example.web.model.request.ShowCardsRequest;
import com.example.web.model.response.Response;

@RestController
@RequestMapping(value="/ryde")
public class RydeController {

	@Autowired
	RydeService rydeService;
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public Response createRyde( @RequestBody RydeCreateRequest rydeRyderBean){
		return rydeService.insertRydeRyderRequest(rydeRyderBean);
	}
	
	@RequestMapping(value="/action" , method=RequestMethod.POST)
	public Response acceptRyde(@RequestBody ActionOnCard action){
		return rydeService.actionOnCard(action);
	}
	
	@RequestMapping(value="/profiles/show",method=RequestMethod.POST)
	public Response showUserProfiles(@RequestBody ShowCardsRequest cards){
		return rydeService.showUserProfiles(cards);
	}
	
	@RequestMapping(value="/view/acted",method=RequestMethod.GET)
	public Response getActedRydes(@RequestParam("userId") int userId){
		return rydeService.getActedRydes(userId);
		
	}
}
