package com.beshambher.socialmedia.dto.response;

import java.util.Date;

import com.beshambher.socialmedia.entity.post.Comment;

public record CommentResponse(
		String id,
		String comment,
		UserResponse user,
		Date createdAt,
		Date updatedAt
) {
	public CommentResponse(Comment comment) {
		this(
				comment.getId(),
				comment.getComment(),
				new UserResponse(comment.getUser()),
				comment.getCreatedAt(),
				comment.getUpdatedAt()
		);
	}
}
