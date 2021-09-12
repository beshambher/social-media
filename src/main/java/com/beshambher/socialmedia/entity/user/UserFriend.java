package com.beshambher.socialmedia.entity.user;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

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
	@JoinColumn(name = "user", referencedColumnName = "username", updatable = false)
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