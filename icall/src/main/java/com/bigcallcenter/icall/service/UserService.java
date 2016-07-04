package com.bigcallcenter.icall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigcallcenter.icall.dao.UserDao;
import com.bigcallcenter.icall.domain.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	public User getMember(String loginName)
	{
		return userDao.getUserByLoginName(loginName);
	}
}
