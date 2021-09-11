package com.beshambher.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Query("SELECT u FROM User u JOIN FETCH u.role r WHERE u.email=?1")
	User findByEmail(String email);

	Long countByUsername(String username);

}
