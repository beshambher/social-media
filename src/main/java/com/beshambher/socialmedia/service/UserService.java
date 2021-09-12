package com.beshambher.socialmedia.service;

import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.beshambher.socialmedia.domain.response.SessionUserResponse;
import com.beshambher.socialmedia.domain.response.UserResponse;
import com.beshambher.socialmedia.entity.user.User;

public interface UserService extends BaseService {

	public User processOAuthUser(OAuth2User authenticationUser);

	public SessionUserResponse getSessionUser(OAuth2User authenticationUser);

	public Page<UserResponse> getUserFriends(String orderBy, String sortBy, Integer page, Integer pageSize);

	public Page<UserResponse> getFriendSuggestions(String orderby, String sortby, Integer page, Integer size);

	public void followUser(String username);

	public void unfollowUser(String username);

}
