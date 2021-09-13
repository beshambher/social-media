package com.beshambher.socialmedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.post.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, String> {

	Page<Post> findByUsername(String username, Pageable pageable);

	Post findByUsernameAndId(String username, String id);

	@Query(value = "SELECT id, body, comments_count, created_at, likes, updated_at, p.username FROM Post p "
			+ "LEFT JOIN User_Friends uf ON p.username=uf.friend WHERE p.username=?1 OR uf.username=?1 GROUP BY p.id", nativeQuery = true)
	Page<Post> findByUserWithFriends(String username, Pageable pageable);

}
