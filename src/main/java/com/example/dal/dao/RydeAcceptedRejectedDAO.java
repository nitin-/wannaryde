package com.example.dal.dao;

import java.util.List;

import com.example.dal.entity.RydeAcceptedRejected;

public interface RydeAcceptedRejectedDAO extends GenericDAO<RydeAcceptedRejected, Integer> {
	
	List<RydeAcceptedRejected> findByUserId(int userId);
	
	List<RydeAcceptedRejected> findByRydeId(int rydeId);

}
