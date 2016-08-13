package com.example.dal.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dal.dao.GenericHibernateDAO;
import com.example.dal.dao.PushNotificationDao;
import com.example.dal.entity.PushNotificationCrd;

@Repository
public class PushNotificationDaoImpl extends GenericHibernateDAO<PushNotificationCrd, Integer> implements PushNotificationDao {
	
	@Autowired
    public PushNotificationDaoImpl(SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
    }
	
	@Override
	public PushNotificationCrd findByUserEmail(String email) {
		Criteria criteria = createCriteria().add(Restrictions.eq("email", email));
		return (PushNotificationCrd) criteria.uniqueResult();
	}

}
