package com.beshambher.socialmedia.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.post.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, String> {

	List<Post> findByUser(String username, Pageable pageable);

	@Query("SELECT p FROM Post p LEFT JOIN p.user u where u.username=?1")
	Page<Post> findByUserWithFriends(String username, Pageable pageable);

}
