package com.beshambher.socialmedia.dto.response;

import java.util.Date;

import com.beshambher.socialmedia.entity.post.Post;

public record PostResponse(
		String id,
		String body,
		Integer likes,
		Integer commentsCount,
		UserResponse user, // Assuming UserResponse is also a record or has a suitable constructor
		Date createdAt,
		Date updatedAt
) {
	public PostResponse(Post post) {
		this(
				post.getId(),
				post.getBody(),
				post.getLikes(),
				post.getCommentsCount(),
				new UserResponse(post.getUser()),
				post.getCreatedAt(),
				post.getUpdatedAt()
		);
	}
}
