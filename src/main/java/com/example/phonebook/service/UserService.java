package com.example.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.phonebook.model.Contact;
import com.example.phonebook.model.User;
import com.example.phonebook.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void createUser(User user) {
		userRepository.save(user);
	}
	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);
		
	}
	public User getUserByUserName(String username) {
		return userRepository.findByUsername(username)	;
	}
	public List<User> getAllUser() {
		return (List<User>) userRepository.findAll();
	}
	public List<Contact> getListOfContact(long id){
		return userRepository.listOfContactForUser(id);
	}
public void updateLoggedIn(boolean loggedIn,long userId) {
		 userRepository.updateLoggedIn(loggedIn,userId);
	}
	public void deleteUser(User user) {
		 userRepository.delete(user);
		
	}
}
