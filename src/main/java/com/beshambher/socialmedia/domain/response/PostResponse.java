package com.beshambher.socialmedia.domain.response;

import java.io.Serializable;
import java.util.Date;

import com.beshambher.socialmedia.entity.post.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String body;
	private Integer likes;
	private Integer commentsCount;
	private User user;
	private Date createdAt;
	private Date updatedAt;

	public PostResponse(Post post) {
		this.id = post.getId();
		this.body = post.getBody();
		this.likes = post.getLikes();
		this.commentsCount = post.getCommentsCount();
		this.user = new User(post.getUser());
		this.createdAt = post.getCreatedAt();
		this.updatedAt = post.getUpdatedAt();
	}

	@Getter
	private class User {

		private String avatar;
		private String username;

		public User(com.beshambher.socialmedia.entity.user.User user) {
			this.avatar = user.getAvatar();
			this.username = user.getUsername();
		}
	}

}
