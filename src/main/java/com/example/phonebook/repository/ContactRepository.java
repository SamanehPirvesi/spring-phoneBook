package com.example.phonebook.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.phonebook.model.Contact;


@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {
	
    public Contact findByName(String name);
	//public boolean updateById(long id , String name) ;
//	public boolean updateTellNumberById(long id , String tell);
    @Modifying
    @Transactional
    @Query("delete from Contact c where c.name = ?1")
   public void deleteByName(String name);
	

}
