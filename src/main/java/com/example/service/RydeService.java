package com.example.service;

import com.example.web.model.request.ActionOnCard;
import com.example.web.model.request.RydeCreateRequest;
import com.example.web.model.request.ShowCardsRequest;
import com.example.web.model.response.Response;

public interface RydeService {

	Response insertRydeRyderRequest(RydeCreateRequest rydeRequestBean);
	
	Response showUserProfiles(ShowCardsRequest cards);
	
	Response actionOnCard(ActionOnCard action);
	
	Response getActedRydes(int userId);
}
