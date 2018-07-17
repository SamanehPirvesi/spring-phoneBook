package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Contact;
import com.example.model.User;
import com.example.repository.UserRepository;

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
	public boolean updateUserById(long id, String name) {
		return userRepository.updateById(id, name);
	}
	public boolean updatePassworldByUsername(String username, String password) {
		return userRepository.updatePasswordByUsername(username, password);
	}
	public void deleteUser(User user) {
		 userRepository.delete(user);
		
	}
}
