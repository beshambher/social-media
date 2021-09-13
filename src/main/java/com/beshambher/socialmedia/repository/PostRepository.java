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

	@Query(value = "SELECT * FROM Post p LEFT JOIN User_Friends uf ON (p.username=uf.username OR p.username=uf.friend) WHERE p.username=?1 OR uf.username=?1", nativeQuery = true)
	Page<Post> findByUserWithFriends(String username, Pageable pageable);

}
