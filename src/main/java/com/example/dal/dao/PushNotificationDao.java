package com.example.dal.dao;

import com.example.dal.entity.PushNotificationCrd;

public interface PushNotificationDao extends GenericDAO<PushNotificationCrd, Integer> {
	
	PushNotificationCrd findByUserEmail(String email);
}
