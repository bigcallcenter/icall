package com.bigcallcenter.icall.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigcallcenter.icall.domain.CustomUserDetails;
import com.bigcallcenter.icall.domain.MenuInfo;
import com.bigcallcenter.icall.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
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
		forwardUrl="forward:/admin/index.do";
		List<MenuInfo> menuInfos = userService.getMenuInfoByUser(username);
		modelMap.addAttribute("menuInfos", menuInfos);
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
