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

	@Query(value = "SELECT u.* FROM users u LEFT JOIN user_friends uf ON u.username=uf.friend WHERE u.username!=?1 AND uf.username=?1", nativeQuery = true)
	Page<User> getUserFriends(String username, Pageable pageable);

	@Query(value = "SELECT * FROM users u WHERE u.username NOT IN (SELECT uf.friend FROM user_friends uf WHERE uf.username=?1) AND u.username!=?1", nativeQuery = true)
	Page<User> getFriendSuggestions(String username, Pageable page);

}
