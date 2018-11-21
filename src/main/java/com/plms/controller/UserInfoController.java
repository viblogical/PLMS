package com.plms.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plms.entity.AvailablePolicies;
import com.plms.entity.User;
import com.plms.entity.UserPolicy;
import com.plms.repository.AvailablePoliciesRepository;
import com.plms.repository.UserInfoRepository;
import com.plms.repository.UserPolicyRepository;
import com.plms.utils.PLMSUtils;

@Controller
@RequestMapping(path = "/plms/ui")
public class UserInfoController {
	@Autowired
	private UserInfoRepository userRepository;
	@Autowired
	private AvailablePoliciesRepository availablePoliciesRepository;
	@Autowired
	private UserPolicyRepository userPolicyRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public ModelAndView addNewUser(ModelAndView modelAndView, @ModelAttribute User ui) {

		User n = new User();
		String msg3 = "";
		try {
			n.setFirstname(ui.getFirstname());
			n.setEmail(ui.getEmail());
			n.setAddress(ui.getAddress());
			n.setContactnumber(ui.getContactnumber());
			n.setDob(PLMSUtils.formatDate(ui.getDob(), "dd/MM/yyyy", "yyyy-MM-dd"));
			n.setUsername(ui.getFirstname() + PLMSUtils.formatDate(ui.getDob(), "dd/MM/yyyy", "ddMM"));
			n.setIsAdmin("N"); // Admins can't be registered via UI
			n.setLastname(ui.getLastname());
			n.setPasswordtext(ui.getPasswordtext());
			if (!checkUsername(n.getUsername())) {
				userRepository.save(n);
				msg3 = "User " + n.getUsername() + " created";
			}else {
				msg3 = "User " + n.getUsername() + " already exist";
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			msg3 = "Invalid Date. It should be in dd/MM/yyyy format";
		} catch (Exception e) {
			e.printStackTrace();
			msg3 = "Invalid Input";
		}
		
		modelAndView.addObject("name", msg3);
		
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@GetMapping(path = "/find/{username}")
	public @ResponseBody boolean checkUsername(@PathVariable("username") String username) {
		// This checks if username is already available
		return userRepository.findByUsername(username).isPresent();
	}

	@PostMapping(value = "/checkPassword")
	public ModelAndView checkPassword( @ModelAttribute User user) {
		String msg = "UserName or Password is incorrect";
		String msg1 = "Contact Admin Service";
		String msg2 = "You are a not registered User. Register to login";
		String msg3 = "";
		Optional<User> u = userRepository.findByUsername(user.getUsername());
		if (u.isPresent())
			// return isAdmin Y for admin and N for user or else ErrorMsg as per user role
			msg3 = u.get().getPasswordtext().equals(user.getPasswordtext()) ? u.get().getIsAdmin()
					: u.get().getIsAdmin().equals("Y") ? msg1 : msg;
		else
			msg3 = msg2;
		
//		model.addAttribute("attribute", "redirectWithRedirectPrefix");

		System.out.println(msg3);
		
		if(msg3.equals("Y")){
			ModelAndView modelAndView=new ModelAndView("redirect:/adminpage");
			Iterable<AvailablePolicies> aps=availablePoliciesRepository.findAll();
			modelAndView.addObject("user", u.get());
			modelAndView.addObject("name", u.get().getUsername());
			modelAndView.addObject("aps", aps);
			modelAndView.setViewName("adminpage");
			return modelAndView;
		}else if (msg3.equals("N")) {
			Iterable<UserPolicy> up=userPolicyRepository.findByUserid(u.get().getUserid());
			Iterable<AvailablePolicies> aps=availablePoliciesRepository.findAll();
			ModelAndView modelAndView=new ModelAndView("redirect:/userpage");
			modelAndView.addObject("user", u.get());
			modelAndView.addObject("name", u.get().getUsername());
			modelAndView.addObject("aps", aps);
			modelAndView.addObject("up", up);
			modelAndView.setViewName("userpage");
			return modelAndView;
		} 
		ModelAndView modelAndView= new ModelAndView("redirect:/plmsclient");
		modelAndView.addObject("name", msg3);
		modelAndView.setViewName("index");
		return modelAndView;
	}

}
