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
		this.avatar = principal.getUser().getAvatar();
		this.username = principal.getUsername();
		this.location = principal.getUser().getLocation();
		this.lastName = principal.getUser().getLastName();
		this.firstName = principal.getUser().getFirstName();
		this.role = principal.getRole();
	}

	public SessionUserResponse(CustomOidcUser principal) {
		this.email = principal.getEmail();
		this.avatar = principal.getUser().getAvatar();
		this.username = principal.getUsername();
		this.location = principal.getUser().getLocation();
		this.lastName = principal.getUser().getLastName();
		this.firstName = principal.getUser().getFirstName();
		this.role = principal.getRole();
	}

}
