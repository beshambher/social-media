package com.beshambher.socialmedia.service.post;

import com.beshambher.socialmedia.dto.request.CommentRequest;
import org.springframework.data.domain.Page;

import com.beshambher.socialmedia.dto.response.CommentResponse;
import com.beshambher.socialmedia.entity.post.Comment;
import com.beshambher.socialmedia.service.CrudService;

public interface CommentService extends CrudService<Comment> {

	Page<CommentResponse> getUserComments(String orderby, String sortby, Integer page, Integer size);

	Page<CommentResponse> getCommentsByPost(String id, String orderby, String sortby, Integer page, Integer size);

	Comment addComment(CommentRequest commentRequest);
}
