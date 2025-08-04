package com.beshambher.socialmedia.entity.user;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserFriendId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user;
	private String friend;

}