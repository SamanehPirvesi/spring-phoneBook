package com.example.phonebook.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.phonebook.model.Contact;
import com.example.phonebook.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUsername(String username) ;
	@Query("select c from Contact c where user_userId=id") 
	public List<Contact> listOfContactForUser(@Param("id") long id);
		
	@Modifying
	@Transactional
    @Query("update User u set u.isLoggedIn= ?1 where u.userId= ?2")
  public void updateLoggedIn( boolean loggedIn , long userId);

}
