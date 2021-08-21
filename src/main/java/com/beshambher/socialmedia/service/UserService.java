package com.beshambher.socialmedia.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.beshambher.socialmedia.entity.user.User;

public interface UserService {

	public User sessionUser(OAuth2User authenticationUser);

}
