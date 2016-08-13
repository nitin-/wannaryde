package com.example.dal.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dal.dao.GenericHibernateDAO;
import com.example.dal.dao.UserDao;
import com.example.dal.entity.User;

@Repository
public class UserDaoImpl extends GenericHibernateDAO<User, Integer> implements UserDao{

	@Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
    }

	@Override
	public User findByEmail(String email) {
		Criteria criteria= createCriteria()
				.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
	}

	@Override
	public List<String> getAllRegisteredDevices() {
		Criteria criteria = createCriteria().
				setProjection(Projections.property("deviceId"));
		return criteria.list();
	}

}
