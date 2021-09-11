package com.beshambher.socialmedia.service.post;

import org.springframework.data.domain.Page;

import com.beshambher.socialmedia.entity.post.Comment;
import com.beshambher.socialmedia.service.CrudService;

public interface CommentService extends CrudService<Comment> {

	public Page<Comment> getCommentsByPost(String postID);

}
