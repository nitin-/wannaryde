package com.example.dal.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dal.dao.GenericHibernateDAO;
import com.example.dal.dao.RydeAcceptedRejectedDAO;
import com.example.dal.entity.RydeAcceptedRejected;

@Repository
public class RydeAcceptedRejectedDAOImpl extends GenericHibernateDAO<RydeAcceptedRejected, Integer> implements RydeAcceptedRejectedDAO {

	@Autowired
	public RydeAcceptedRejectedDAOImpl(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}

	@Override
	public List<RydeAcceptedRejected> findByUserId(int userId) {
		Criteria criteria = createCriteria().add(Restrictions.eq("user.id", userId));
		return criteria.list();
	}

	@Override
	public List<RydeAcceptedRejected> findByRydeId(int rydeId) {
		Criteria criteria = createCriteria().add(Restrictions.eq("rydeRyderRequest.id", rydeId));
		return criteria.list();
	}
}
