package com.example.phonebook.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.phonebook.model.User;
import com.example.phonebook.service.UserService;


@Controller
public class PhoneBookController {
	 @Autowired
	 private UserService userService;
	 
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getIndex() {
		return "login";
	}
	@RequestMapping(value="/registerUser", method=RequestMethod.GET)
	public String registerUser() {
		return "registerUser";
	}
	@PostMapping("/doRegisterUser")
	public ModelAndView registerUser(@ModelAttribute("user") User u) {		
		 userService.createUser(u);
		ModelAndView model=new ModelAndView("login");
		return model;
	}
	
	@PostMapping("/doLogin")
	public ModelAndView manageLogin(@Valid @ModelAttribute("user") User u, BindingResult resultBinding ) {	
		User readedUser = userService.getUserByUserName(u.getUsername());
		ModelAndView model =null;
		if( readedUser != null && readedUser.getPassword().equals(u.getPassword())) {
			model=new ModelAndView("userHome");
			userService.updateLoggedIn(true,readedUser.getUserId());
			model.addObject("username",u.getUsername());
			
			
		}else { 
			
			model=new ModelAndView("login");
		}
           			
		return model;
	}
	
	@RequestMapping(value="/userPortal/{username}", method=RequestMethod.GET)
	public ModelAndView userPortal(@PathVariable("username") String username) {
		ModelAndView model=new ModelAndView("userHome");
		model.addObject("username", username);
		return model;
	}
	
	
		@RequestMapping(value="/doLogout/{username}", method=RequestMethod.GET)
		public ModelAndView doLogout(@PathVariable("username") String username) {
			ModelAndView model=new ModelAndView("login");
			User readedUser = userService.getUserByUserName(username);
			userService.updateLoggedIn(false,readedUser.getUserId());		
			return model;
		}
}