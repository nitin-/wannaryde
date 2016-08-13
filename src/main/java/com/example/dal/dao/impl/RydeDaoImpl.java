package com.example.dal.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dal.dao.GenericHibernateDAO;
import com.example.dal.dao.RydeDao;
import com.example.dal.entity.RydeRyderRequest;

@Repository
public class RydeDaoImpl extends GenericHibernateDAO<RydeRyderRequest, Integer> implements RydeDao {
	
	@Autowired
    public RydeDaoImpl(SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
    }

	@Override
	public List<RydeRyderRequest> findRydesExceptForUserId(int userId) {
		Criteria criterion = createCriteria()
				.add(Restrictions.ne("user.id", userId));
		return (List<RydeRyderRequest>)criterion.list();
	}

	@Override
	public List<RydeRyderRequest> findRydesExceptForUserIdAndUserType(int userId, String userType) {
		Criteria criteria = createCriteria()
				.add(Restrictions.and(Restrictions.ne("user.id", userId),Restrictions.eq("userType", userType)));
		return criteria.list();
	}
}
