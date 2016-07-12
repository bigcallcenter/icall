package com.bigcallcenter.icall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigcallcenter.icall.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/getAllUsers.do")
	public String getAllUsers(HttpServletRequest request,ModelMap modelMap)
	{
		
		return "user/userList";
	}
	
}
