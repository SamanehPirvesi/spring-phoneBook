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

import com.example.phonebook.model.Contact;
import com.example.phonebook.model.User;
import com.example.phonebook.service.ContactService;
import com.example.phonebook.service.UserService;


@Controller
public class PhoneBookController {
	 @Autowired
	 private UserService userService;
	 @Autowired
	 private ContactService contactService;
	
	
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
			u.setUserId(readedUser.getUserId());
			model=new ModelAndView("userHome");
			
		}else { 
			
		//	userService.updateLoggedIn(readedUser.getUserId(),true);
			model=new ModelAndView("login");
		}
           			
		return model;
	}
	
	@RequestMapping(value="/registerContact/{username}", method=RequestMethod.GET)
	public ModelAndView registerContact(@PathVariable("username") String username) {
		ModelAndView model=new ModelAndView("registerContact");
		model.addObject("username", username);
		return model;
	}
	
	@PostMapping("/doRegisterContact/{username}")
	public ModelAndView doRegisterUser(@PathVariable("username") String username,@ModelAttribute("contact") Contact c) {		
		User readedUser = userService.getUserByUserName(username);
		readedUser.addContact(c);
		c.setUser(readedUser);
		contactService.createContact(c);
		ModelAndView model=new ModelAndView("listOfContacts");
		model.addObject("user",readedUser);
		return model;
	}
	
		@RequestMapping(value="/listOfContacts/{username}", method=RequestMethod.GET)
	public ModelAndView listOfContact(@PathVariable("username") String username) {
		ModelAndView model=new ModelAndView("listOfContacts");
		User readedUser = userService.getUserByUserName(username);
		model.addObject("user",readedUser);
		return model;
}
}