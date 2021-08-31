package com.beshambher.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	User findByEmail(String email);
	
	Long countByUsername(String username);

}
