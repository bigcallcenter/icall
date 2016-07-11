package com.bigcallcenter.icall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bigcallcenter.icall.domain.MenuInfo;
import com.bigcallcenter.icall.service.UserService;

@Controller
public class LoginController {
	@Autowired
    private AuthenticationManager myAuthenticationManager;
	
	
	@RequestMapping(value = "/loginpage.do", method = RequestMethod.GET)
    public ModelAndView loginpage(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,ModelMap modelMap) {

               ModelAndView model = new ModelAndView();
        if (error != null) {
        	modelMap.put("error", "Invalid username and password!");
        }
        if (logout != null) {
        	modelMap.put("msg", "You've been logged out successfully.");
        }
        return new ModelAndView("login",modelMap);

    }
	
    @RequestMapping(value = "/login.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String login(@RequestParam(defaultValue="") String username,
    		@RequestParam(defaultValue="") String password,
    		HttpServletRequest request,ModelMap modelMap){
    	 HttpSession session = request.getSession();
    	 session.setAttribute("USERNAME", username);
		modelMap.addAttribute("username", username);
		modelMap.addAttribute("test", "测试传递参数");
		return "forward:/admin/index.do";
    }
	
	 
}
