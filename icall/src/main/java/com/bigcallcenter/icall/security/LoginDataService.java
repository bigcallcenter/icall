package com.bigcallcenter.icall.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bigcallcenter.icall.dao.UserDao;
import com.bigcallcenter.icall.domain.CustomUserDetails;
import com.bigcallcenter.icall.domain.User;

public class LoginDataService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.getUserByLoginName(username);
		if(user==null)
		{
			throw new UsernameNotFoundException(username);
		}
		CustomUserDetails user1 = new CustomUserDetails(user);
		return user1;
	}
}
