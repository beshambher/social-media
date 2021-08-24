package com.beshambher.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beshambher.socialmedia.domain.response.SessionUserResponse;
import com.beshambher.socialmedia.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/session/user")
	public SessionUserResponse getLoggedInUser(@AuthenticationPrincipal OAuth2User principal) {
		return userService.getSessionUser(principal);
	}
}
