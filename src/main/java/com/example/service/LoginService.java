package com.example.service;

import java.util.ArrayList;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dal.dao.UserDao;
import com.example.dal.entity.User;
import com.example.web.model.response.UserDetailsResponse;
 
 
@Service
@Transactional(readOnly=true)
public class LoginService implements UserDetailsService{
 
	@Autowired
	UserDao userDao;
	
	@Autowired
	Mapper mapper;
	
    @Override
    public UserDetails loadUserByUsername(String email)  throws UsernameNotFoundException {
    	User user = userDao.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Username not found"); 
        }
        
        UserDetailsResponse userdeatils =  new UserDetailsResponse(user, true, true, true, true, new ArrayList<GrantedAuthority>());
        mapper.map(user, userdeatils);
        return userdeatils;
        /*return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
                 user.isEnabled(), true, true, true, getGrantedAuthorities(user));*/
    }
 
     
    /*private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName().toUpperCase()));
        return authorities;
    }*/
    
}