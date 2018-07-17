package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Contact;


@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {
	
    public Contact findByName(String name);
	public boolean updateById(long id , String name) ;
	public boolean updateTellNumberById(long id , String tell);
	public boolean deleteByName(String name);
	

}
