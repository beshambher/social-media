package com.beshambher.socialmedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.post.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {

	Page<Comment> findByPost(String postId, Pageable pageable);

	Page<Comment> findByUser(String username, Pageable pageable);

}
