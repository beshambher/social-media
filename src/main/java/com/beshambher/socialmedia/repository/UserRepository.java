package com.beshambher.socialmedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Query("SELECT u FROM User u JOIN FETCH u.role r WHERE u.email=?1")
	User findByEmail(String email);

	User findByUsername(String username);

	Long countByUsername(String username);

	@Query(value = "SELECT * FROM Users u LEFT JOIN User_Friends uf ON (u.username=uf.user OR u.username=uf.friend) "
			+ "WHERE u.username!=?1 AND (uf.user=?1 OR uf.friend=?1)", nativeQuery = true)
	Page<User> getUserFriends(String username, Pageable pageable);

	@Query(value = "SELECT * FROM Users u LEFT JOIN User_Friends uf ON (u.username=uf.user OR u.username=uf.friend) "
			+ "WHERE u.username!=?1 AND (uf.user IS NULL OR (uf.user!=?1 AND uf.friend!=?1))", nativeQuery = true)
	Page<User> getFriendSuggestions(String username, Pageable page);

}
