package com.example.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dal.dao.RydeAcceptedRejectedDAO;
import com.example.dal.dao.RydeDao;
import com.example.dal.dao.UserDao;
import com.example.dal.entity.RydeAcceptedRejected;
import com.example.dal.entity.RydeRyderRequest;
import com.example.dal.entity.User;
import com.example.service.PushService;
import com.example.service.RydeService;
import com.example.service.util.LatLong;
import com.example.service.util.Util;
import com.example.web.model.request.ActionOnCard;
import com.example.web.model.request.RydeCreateRequest;
import com.example.web.model.request.ShowCardsRequest;
import com.example.web.model.response.Response;
import com.example.web.util.Constants;

@Service
@Transactional
public class RydeServiceImpl implements RydeService{
	
	@Autowired
	RydeDao rydeRyderDao;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RydeAcceptedRejectedDAO rydeActionDao;
	
	@Autowired
	PushService pushService;
	
	@Override
	public Response insertRydeRyderRequest(RydeCreateRequest rydeRequestBean) {
		Response res = Util.getResponseInstance();
		try{
			RydeRyderRequest rydeEntity = mapper.map(rydeRequestBean, RydeRyderRequest.class);
			Date date = new Date();
			rydeEntity.setCreatedAt(date);
			rydeEntity.setUpdatedAt(date);
			User user = userDao.findByID(rydeRequestBean.getUserId());
			rydeEntity.setUser(user);
			res.setData(rydeRyderDao.save(rydeEntity));
			res.setStatus(200);
			res.setMessage("Successfully created Ryde");
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(500);
			res.setData(null);
		}
		return res;
	}

	@Override
	public Response showUserProfiles(ShowCardsRequest cards) {
		Response res = Util.getResponseInstance();
		try{
			User user = userDao.findByID(cards.getUserId());
			LatLong ll = Util.getLatLong(user.getSourceLandMarkLatLong(), user.getDestinationLandMarkLatLong());
			if (null != user) {
				double sourceLat = cards.getLatitude() != 0.0?cards.getLatitude():ll.getSourceLat();
				double sourceLong = cards.getLongitude() != 0.0?cards.getLongitude():ll.getSourceLong();
				List<RydeRyderRequest> rydeList =null;
				if(Constants.UserType.RYDEE.toString().equals(user.getUserType())){
					 rydeList = rydeRyderDao.findRydesExceptForUserIdAndUserType(cards.getUserId(), Constants.UserType.RYDER.toString());
				}else if(Constants.UserType.RYDER.toString().equals(user.getUserType())){
					 rydeList = rydeRyderDao.findRydesExceptForUserIdAndUserType(cards.getUserId(), Constants.UserType.RYDEE.toString());
				}
				if(null != rydeList){
					prepareBestCards(sourceLat, sourceLong, rydeList);
					Collections.sort(rydeList);
				}
					res.setData(rydeList);
				res.setMessage("Successfully Prepared Cards");
				res.setStatus(200);
			}
		}catch(Exception e){
			res.setData(e.getCause());
			res.setMessage("Error in processing best card");
			res.setStatus(500);
			e.printStackTrace();
		}
		return res;
	}
	
	private void prepareBestCards(double sourceLat, double sourceLong,List<RydeRyderRequest> rydeList){
		for(RydeRyderRequest req:rydeList){
			 double distance = Util.distBetweenPoints(sourceLat, sourceLong, req.getSourceLatitude(), req.getSourceLongitude());
			 req.setDistanceFromSource(distance);
		 }
	}

	@Override
	public Response actionOnCard(ActionOnCard action) {
		Response res = Util.getResponseInstance();
		try{
			RydeAcceptedRejected actionOnCard = rydeActionDao.findByID(action.getCardId());
			Date date = new Date();
			if(null == actionOnCard){
				actionOnCard = new RydeAcceptedRejected();
				actionOnCard.setAccepted(action.getAccepted());
				actionOnCard.setActionUserType(action.getUserType());
				RydeRyderRequest rydeRyderRequest = rydeRyderDao.findByID(action.getRydeId());
				if(action.getAccepted()){
					User u = rydeRyderRequest.getUser();
					if(null != u){
						/*List<String> androidTargets = new ArrayList<String>();
						androidTargets.add(u.getDeviceId());
						pushService.pushToAndroid("Accept Ryde Request", "Congratulations! card accepted", androidTargets);*/
					}
				}
				actionOnCard.setRydeRyderRequest(rydeRyderRequest);
				User user = userDao.findByID(action.getUserId());
				actionOnCard.setUser(user);
				actionOnCard.setCreatedAt(date);
				actionOnCard.setUpdatedAt(date);
			}else{
				actionOnCard.setUpdatedAt(date);
				actionOnCard.setAccepted(action.getAccepted());
			}
			res.setData(rydeActionDao.save(actionOnCard));
			res.setStatus(200);
			if(action.getAccepted()){
				res.setMessage("Ryde Accepted Successfully");
			}else{
				res.setMessage("Ryde Rejected Successfully");
			}
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(500);
			if(action.getAccepted()){
				res.setMessage("Ryde Accepted Successfully");
			}else{
				res.setMessage("Ryde Rejected Successfully");
			}
			res.setData(null);
		}
		return res;
	}

	@Override
	public Response getActedRydes(int userId) {
		Response res = Util.getResponseInstance();
		try{
			res.setData(rydeActionDao.findByUserId(userId));
			res.setMessage("Successfully Fetched List");
			res.setStatus(200);
		}catch(Exception e){
			e.printStackTrace();
			res.setData(null);
			res.setMessage("Error in  Fetching List");
			res.setStatus(500);
		}
		return res;
	}
}
