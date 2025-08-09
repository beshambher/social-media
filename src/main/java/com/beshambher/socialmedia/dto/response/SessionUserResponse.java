package com.beshambher.socialmedia.dto.response;

import com.beshambher.socialmedia.domain.oauth.CustomOAuth2User;
import com.beshambher.socialmedia.domain.oauth.CustomOidcUser;

public record SessionUserResponse(
		String email,
		String avatar,
		String username,
		String location,
		String lastName,
		String firstName,
		String role
) {
	public SessionUserResponse(CustomOAuth2User principal) {
		this(
				principal.getEmail(),
				principal.getUser().getAvatar(),
				principal.getUsername(),
				principal.getUser().getLocation(),
				principal.getUser().getLastName(),
				principal.getUser().getFirstName(),
				principal.getRole()
		);
	}

	public SessionUserResponse(CustomOidcUser principal) {
		this(
				principal.getEmail(),
				principal.getUser().getAvatar(),
				principal.getUsername(),
				principal.getUser().getLocation(),
				principal.getUser().getLastName(),
				principal.getUser().getFirstName(),
				principal.getRole()
		);
	}
}
