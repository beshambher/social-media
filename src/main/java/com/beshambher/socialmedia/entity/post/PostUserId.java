package com.beshambher.socialmedia.entity.post;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PostUserId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user;
	private String post;

}