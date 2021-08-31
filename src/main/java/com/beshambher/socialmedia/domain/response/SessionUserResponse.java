package com.beshambher.socialmedia.domain.response;

import com.beshambher.socialmedia.domain.oauth.CustomOAuth2User;
import com.beshambher.socialmedia.domain.oauth.CustomOidcUser;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SessionUserResponse {

	private String email;
	private String avatar;
	private String username;
	private String location;
	private String lastName;
	private String firstName;
	private String role;

	public SessionUserResponse(CustomOAuth2User principal) {
		this.email = principal.getEmail();
		this.avatar = principal.getAvatar();
		this.username = principal.getUsername();
		this.location = principal.getLocation();
		this.lastName = principal.getLastName();
		this.firstName = principal.getFirstName();
		this.role = principal.getRole();
	}

	public SessionUserResponse(CustomOidcUser principal) {
		this.email = principal.getEmail();
		this.avatar = principal.getAvatar();
		this.username = principal.getUsername();
		this.location = principal.getLocation();
		this.lastName = principal.getLastName();
		this.firstName = principal.getFirstName();
		this.role = principal.getRole();
	}

}
