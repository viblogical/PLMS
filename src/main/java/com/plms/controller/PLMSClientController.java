package com.plms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.plms.entity.AvailablePolicies;
import com.plms.entity.User;

@Controller
public class PLMSClientController {

    @GetMapping("/plmsclient")
	public ModelAndView showHomePage(ModelAndView modelAndView,User user){
	Object name =modelAndView.getModel().get("name");	
		if (name!=null) {
			modelAndView.addObject("name", name);
		}else {
		modelAndView.addObject("name", "Guest");
		}
		modelAndView.setViewName("index");
		return modelAndView;
	}
    
    @GetMapping("/register")
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.addObject("name", "Guest");
		modelAndView.setViewName("register");
		return modelAndView;
	}
    
    @GetMapping("/userpage")
	public ModelAndView showUserPage(ModelAndView modelAndView,@ModelAttribute User user){
//		modelAndView.setViewName("userpage");
//		modelAndView.addObject("user", user);
//		modelAndView.addObject("name", user.getUsername());
//		System.out.println(user.getFirstname());
		return modelAndView;
	}
    
    @GetMapping("/adminpage")
	public ModelAndView showAdminPage(ModelAndView modelAndView,@ModelAttribute AvailablePolicies ap){
//    	modelAndView.addObject("ap", ap);
//		modelAndView.addObject("user", user);
//		modelAndView.addObject("name", user.getUsername());
//		System.out.println(user.getFirstname());
		return modelAndView;
	}

}