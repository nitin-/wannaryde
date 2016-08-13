package com.example.dal.dao;

import java.util.List;

import com.example.dal.entity.User;

public interface UserDao extends GenericDAO<User, Integer>{

	User findByEmail(String email);	
	
	List<String> getAllRegisteredDevices();
}