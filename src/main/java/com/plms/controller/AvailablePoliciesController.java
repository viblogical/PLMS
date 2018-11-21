package com.plms.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plms.entity.AvailablePolicies;
import com.plms.entity.User;
import com.plms.repository.AvailablePoliciesRepository;

@Controller
@RequestMapping(path="/plms/ap") 
public class AvailablePoliciesController {
	@Autowired
	private AvailablePoliciesRepository availablePoliciesRepository;


	@GetMapping(path="/all")
	public @ResponseBody Iterable<AvailablePolicies> fetchAllPolicies()  {
		// This returns all policies
		return availablePoliciesRepository.findAll();
	}
	@PostMapping(value = "/editPolicy")
	public ModelAndView editPolicy( ModelAndView modelAndView, @RequestParam Integer policyid
			, @RequestParam String policyname, @RequestParam String policydetails,@RequestParam String name) {
		System.out.println(policyid);
		System.out.println(policyname);
		System.out.println(policydetails);
		
//		modelAndView.addObject("user", u.get());
		modelAndView.addObject("name", name);
		
		AvailablePolicies ap = new AvailablePolicies();
		ap.setPolicydetails(policydetails);
		ap.setPolicyid(policyid);
		ap.setPolicyname(policyname);
		availablePoliciesRepository.save(ap);
		Iterable<AvailablePolicies> aps=availablePoliciesRepository.findAll();
		modelAndView.addObject("aps", aps);
		modelAndView.setViewName("adminpage");
		return modelAndView;
	}


}
