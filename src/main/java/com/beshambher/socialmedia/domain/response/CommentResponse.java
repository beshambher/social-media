package com.beshambher.socialmedia.domain.response;

import java.io.Serializable;
import java.util.Date;

import com.beshambher.socialmedia.entity.post.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String comment;
	private UserResponse user;
	private Date createdAt;
	private Date updatedAt;

	public CommentResponse(Comment comment) {
		this.id = comment.getId();
		this.comment = comment.getComment();
		this.user = new UserResponse(comment.getUser());
		this.createdAt = comment.getCreatedAt();
		this.updatedAt = comment.getUpdatedAt();
	}

}
