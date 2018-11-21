package com.plms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plms.entity.UserPolicy;
import com.plms.repository.UserInfoRepository;
import com.plms.repository.UserPolicyRepository;

@Controller
@RequestMapping(path="/plms/up") 
public class UserPolicyController {
	@Autowired
	private UserPolicyRepository userPolicyRepository;
	@Autowired 
	private UserInfoRepository userRepository; 


	@GetMapping(path="/all/{username}")
	public @ResponseBody Iterable<UserPolicy> fetchAllPolicies(@PathVariable("username") String username)  {
		// This returns all policies
		if(userRepository.findByUsername(username).isPresent())
		return userPolicyRepository.findByUserid(userRepository.findByUsername(username).get().getUserid());
		return null;
	}



}
