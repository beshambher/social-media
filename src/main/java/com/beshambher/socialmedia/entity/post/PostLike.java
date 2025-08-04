package com.beshambher.socialmedia.entity.post;

import com.beshambher.socialmedia.entity.user.User;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post_likes")
public class PostLike {

	@EmbeddedId
	private PostUserId id;

	@ManyToOne
	@MapsId("user")
	@JoinColumn(name = "username", referencedColumnName = "username", updatable = false)
	private User user;

	@ManyToOne
	@MapsId("post")
	@JoinColumn(name = "post", referencedColumnName = "id", updatable = false)
	private Post post;

	public PostLike(User user, Post post) {
		super();
		this.user = user;
		this.post = post;
		this.id = new PostUserId(user.getUsername(), post.getId());
	}

}