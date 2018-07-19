package com.example.phonebook.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.phonebook.model.Contact;
import com.example.phonebook.repository.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;
	
	public void createContact(Contact contact) {
		contactRepository.save(contact);
	}
	public Optional<Contact> getContactById(long id) {
		return contactRepository.findById(id);
	}
	public Contact getContactByName(String name) {
		return contactRepository.findByName(name);
	}
//	public void updateContactById(long id , String name ){
//		contactRepository.updateById(id, name);
//		}
//	public void updateContactTellNumberById(long id , String tell) {
//		contactRepository.updateTellNumberById(id, tell);
//	}
	public void deleteContact(Contact contact) {
		contactRepository.delete(contact);
	}
	public void deleteContactByName(String name) {
		 contactRepository.deleteByName(name);
	}

}
