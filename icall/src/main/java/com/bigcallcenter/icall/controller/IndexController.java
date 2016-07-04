package com.bigcallcenter.icall.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigcallcenter.icall.domain.CustomUserDetails;

@Controller
public class IndexController {

	@RequestMapping("/index/main.do")
	public String indexMain(HttpServletRequest request,ModelMap modelMap)
	{
		String username="";
		String password="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      if (principal instanceof UserDetails) {
	    	  username= ((CustomUserDetails) principal).getUsername();
	    	  password=((CustomUserDetails) principal).getPassword();
	      }
	      if (principal instanceof Principal) {
	    	  username= ((Principal) principal).getName();
	      }
	      HttpSession session = request.getSession();
	    	 session.setAttribute("USERNAME", username);
		modelMap.addAttribute("username", username);
		modelMap.addAttribute("password", password);
		String forwardUrl="";
		if(username.startsWith("admin"))
		{
			forwardUrl="forward:/admin/index.do";
		}
		else
		{
			 if(username.startsWith("teacher"))
			 {
				 forwardUrl="forward:/schoolAdmin/index.do";
			 }
			 else
			 {
				 forwardUrl="forward:/teacher/index.do";
			 }
		}
		
		return forwardUrl;
	}
	
	@RequestMapping("/admin/index.do")
	public String adminIndex()
	{
		return "/admin/index";
	}
	
	@RequestMapping("/admin/sockettest.do")
	public String sockettest()
	{
		return "/admin/index2";
	}
}
