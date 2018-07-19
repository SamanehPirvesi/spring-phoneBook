package com.example.phonebook.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class ContactController {
	 @Autowired
	 private UserService userService;
	@Autowired
	 private ContactService contactService;
	
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
	@PostMapping("/doDeleteContact/{contactName}")
	public ModelAndView doDeleteContact(@PathVariable("contactName") String contactName) {	
		ModelAndView model=new ModelAndView("userHome");
		Contact contact=contactService.getContactByName(contactName);
		contactService.deleteContactByName(contactName);
		model.addObject("username",contact.getUser().getUsername());
		return model;
	}
	
}
