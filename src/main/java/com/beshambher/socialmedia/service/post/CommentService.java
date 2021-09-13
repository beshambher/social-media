package com.beshambher.socialmedia.service.post;

import org.springframework.data.domain.Page;

import com.beshambher.socialmedia.domain.response.CommentResponse;
import com.beshambher.socialmedia.entity.post.Comment;
import com.beshambher.socialmedia.service.CrudService;

public interface CommentService extends CrudService<Comment> {

	public Page<CommentResponse> getUserComments(String orderby, String sortby, Integer page, Integer size);

	public Page<CommentResponse> getCommentsByPost(String id, String orderby, String sortby, Integer page, Integer size);

}
