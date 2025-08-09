package com.beshambher.socialmedia.service;

import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.beshambher.socialmedia.dto.response.SessionUserResponse;
import com.beshambher.socialmedia.dto.response.UserResponse;
import com.beshambher.socialmedia.entity.user.User;

public interface UserService extends BaseService {

	User processOAuthUser(OAuth2User authenticationUser);

	SessionUserResponse getSessionUser(OAuth2User authenticationUser);

	Page<UserResponse> getUserFriends(String orderBy, String sortBy, Integer page, Integer pageSize);

	Page<UserResponse> getFriendSuggestions(String orderby, String sortby, Integer page, Integer size);

	void followUser(String username);

	void unfollowUser(String username);

}
