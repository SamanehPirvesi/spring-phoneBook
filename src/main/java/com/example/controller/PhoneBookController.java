package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class PhoneBookController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/phonebook")
	public String getlogin() {
		return "login";
	}
	public ModelAndView manageLogin(@Valid @ModelAttribute("user") User u,BindingResult resultBinding ) {
		ModelAndView model =null;
		if(resultBinding.hasErrors()) {			
			 model=new ModelAndView("login");
		}else {
			// no error
			model=new ModelAndView("bb/test");	
	
		}
		return model;
	}
	
	}
