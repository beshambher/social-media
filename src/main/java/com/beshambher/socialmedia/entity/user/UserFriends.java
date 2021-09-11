package com.beshambher.socialmedia.entity.user;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_friends")
public class UserFriends {

	@EmbeddedId
	private UserFriendId id;

	@ManyToOne
	@MapsId("username")
	private User user;

	@ManyToOne
	@MapsId("username")
	private User friend;

	@Getter
	@Embeddable
	public class UserFriendId implements Serializable {
		private static final long serialVersionUID = 1L;
		private String user;
		private String friend;
	}
}