package com.bigcallcenter.icall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigcallcenter.icall.dao.MenuDao;
import com.bigcallcenter.icall.dao.UserDao;
import com.bigcallcenter.icall.domain.MenuInfo;
import com.bigcallcenter.icall.domain.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MenuDao menuDao;
	
	
	
	
	public User getMember(String loginName)
	{
		return userDao.getUserByLoginName(loginName);
	}
	
	public List<MenuInfo> getMenuInfoByUser(String loginName){
		return menuDao.getMenuInfoByUser(loginName);
	}
}
