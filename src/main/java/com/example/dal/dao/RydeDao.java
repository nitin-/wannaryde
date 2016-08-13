package com.example.dal.dao;

import java.util.List;

import com.example.dal.entity.RydeRyderRequest;


public interface RydeDao extends GenericDAO<RydeRyderRequest, Integer>{
	
	List<RydeRyderRequest> findRydesExceptForUserId(int userId);
	
	List<RydeRyderRequest> findRydesExceptForUserIdAndUserType(int userId,String userType);
}
