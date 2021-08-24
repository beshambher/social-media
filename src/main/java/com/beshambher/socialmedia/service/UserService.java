package com.beshambher.socialmedia.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.beshambher.socialmedia.domain.response.SessionUserResponse;
import com.beshambher.socialmedia.entity.user.User;

public interface UserService {

	public User processOAuthUser(OAuth2User authenticationUser);

	public SessionUserResponse getSessionUser(OAuth2User authenticationUser);

}
