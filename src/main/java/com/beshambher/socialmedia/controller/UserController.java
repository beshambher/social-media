package com.beshambher.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beshambher.socialmedia.domain.response.SessionUserResponse;
import com.beshambher.socialmedia.domain.response.UserResponse;
import com.beshambher.socialmedia.entity.user.User;
import com.beshambher.socialmedia.service.UserService;

@RestController
@RequestMapping("/social")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/session/user")
	public SessionUserResponse getLoggedInUser(@AuthenticationPrincipal OAuth2User principal) {
		return userService.getSessionUser(principal);
	}

	@GetMapping("/user/friends")
	public Page<UserResponse> getUserFriends(@RequestParam(required = false) String orderby,
			@RequestParam(required = false) String sortby, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return userService.getUserFriends(orderby, sortby, page, size);
	}

	@GetMapping("/user/suggestions")
	public Page<UserResponse> getFriendSuggestions(@RequestParam(required = false) String orderby,
			@RequestParam(required = false) String sortby, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return userService.getFriendSuggestions(orderby, sortby, page, size);
	}

	@PutMapping("/user/{username}/follow")
	public ResponseEntity<User> followUser(@PathVariable String username) {
		userService.followUser(username);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/user/{username}/unfollow")
	public ResponseEntity<User> unfollowUser(@PathVariable String username) {
		userService.unfollowUser(username);
		return ResponseEntity.noContent().build();
	}
	
}
