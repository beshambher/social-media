package com.beshambher.socialmedia.entity.user;

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
@Table(name = "user_friends")
public class UserFriend {

	@EmbeddedId
	private UserFriendId id;

	@ManyToOne
	@MapsId("user")
	@JoinColumn(name = "username", referencedColumnName = "username", updatable = false)
	private User user;

	@ManyToOne
	@MapsId("friend")
	@JoinColumn(name = "friend", referencedColumnName = "username", updatable = false)
	private User friend;

	public UserFriend(User user, User friend) {
		super();
		this.user = user;
		this.friend = friend;
		this.id = new UserFriendId(user.getUsername(), friend.getUsername());
	}

}