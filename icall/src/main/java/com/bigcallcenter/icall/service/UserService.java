package com.bigcallcenter.icall.service;

import java.util.List;

import org.apache.log4j.Logger;
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
	
	
	private Logger logger = Logger.getLogger(UserService.class);
	
	public User getMember(String loginName)
	{
		return userDao.getUserByLoginName(loginName);
	}
	
	public List<MenuInfo> getMenuInfoByUser(String loginName){
		List<MenuInfo> menuInfos = null;
		try {
			menuInfos = menuDao.getMenuInfoByUser(loginName);
		} catch (Exception e) {
			logger.equals(e);
		}
		return menuInfos;
	}
	
	public List<User> getAllUsers(){
		List<User> users = null;
		try {
			users = userDao.getAllusers();
		} catch (Exception e) {
			logger.error(e);
		}
		return users;
	}
}
