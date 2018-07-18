package com.example.phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.phonebook.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	 private ContactService contactService;
	
}
