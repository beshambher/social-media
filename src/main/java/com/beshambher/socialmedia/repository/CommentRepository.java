package com.beshambher.socialmedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.post.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {

	@Query("SELECT c FROM Comment c WHERE c.post.id=?1")
	Page<Comment> findByPost(String postId, Pageable pageable);

	Page<Comment> findByUser(String username, Pageable pageable);

	Comment findByUserAndId(String username, String id);

	@Modifying
	@Query("DELETE FROM Comment c WHERE c.post.id=?1")
	void deleteByPost(String postId);
}
