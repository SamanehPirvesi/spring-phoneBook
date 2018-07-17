package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Contact;
import com.example.repository.ContactRepository;

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
	public void updateContactById(long id , String name ){
		contactRepository.updateById(id, name);
		}
	public void updateContactTellNumberById(long id , String tell) {
		contactRepository.updateTellNumberById(id, tell);
	}
	public void deleteContact(Contact contact) {
		contactRepository.delete(contact);
	}
	public boolean deleteContactByName(String name) {
		return contactRepository.deleteByName(name);
	}

}
