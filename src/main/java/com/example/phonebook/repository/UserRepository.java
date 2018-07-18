package com.example.phonebook.repository;

import java.util.List;

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
	//public boolean updateById(long id, String name) ;
	@Query("select c from Contact c where user_userId=id") 
	 public List<Contact> listOfContactForUser(@Param("id") long id);

	@Modifying
    @Query("UPDATE User u SET u.isLoggedIn =:loggedin WHERE u.userId=:userId")
  public void updateLoggedIn(@Param("userId") long userId, @Param("loggedin") boolean loggedIn);

	

}
