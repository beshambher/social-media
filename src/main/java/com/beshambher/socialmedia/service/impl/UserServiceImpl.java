package com.beshambher.socialmedia.service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.beshambher.socialmedia.constants.Constant;
import com.beshambher.socialmedia.domain.oauth.CustomOAuth2User;
import com.beshambher.socialmedia.domain.oauth.CustomOidcUser;
import com.beshambher.socialmedia.domain.response.SessionUserResponse;
import com.beshambher.socialmedia.domain.response.UserResponse;
import com.beshambher.socialmedia.entity.authority.Role;
import com.beshambher.socialmedia.entity.user.User;
import com.beshambher.socialmedia.repository.RoleRepository;
import com.beshambher.socialmedia.repository.UserRepository;
import com.beshambher.socialmedia.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public User processOAuthUser(OAuth2User oAuthUser) {
		User user = userRepository.findByEmail((String) oAuthUser.getAttribute("email"));

		if (user == null) {
			Map<String, Object> userAttributes = oAuthUser.getAttributes();
			Role defaultRole = roleRepository.findByName(Constant.Role.ROLE_USER.toString());
			user = new User();
			user.setRole(defaultRole);
			String email = (String) userAttributes.get("email");
			String firstName = (String) userAttributes.get("name");
			String username = (String) userAttributes.get("login");
			if (username == null || username.equals("")) {
				username = email.substring(0, email.indexOf("@"));
			}
			Long usernameCount = userRepository.countByUsername(username);
			if (usernameCount > 0) {
				username += usernameCount;
			}
			user.setEmail(email);
			user.setUsername(username);
			if (firstName.indexOf(' ') > 0) {
				user.setLastName(firstName.substring(firstName.indexOf(' ') + 1));
				firstName = firstName.substring(0, firstName.indexOf(' '));
			}
			user.setFirstName(firstName);
			user.setLocation((String) userAttributes.get("location"));
			user.setAvatar((String) userAttributes.getOrDefault("avatar_url", "")
					+ (String) userAttributes.getOrDefault("picture", ""));
			user = userRepository.save(user);
		}

		return user;
	}

	@Override
	public SessionUserResponse getSessionUser(OAuth2User authenticationUser) {
		if (authenticationUser instanceof CustomOidcUser) {
			return new SessionUserResponse((CustomOidcUser) authenticationUser);
		}
		return new SessionUserResponse((CustomOAuth2User) authenticationUser);
	}

	@Override
	public Page<UserResponse> getUserFriends(String orderBy, String sortBy, Integer page, Integer pageSize) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.getUserFriends(username, getPage(orderBy, sortBy, page, pageSize))
				.map(u -> new UserResponse(u));
	}

	@Override
	public Page<UserResponse> getFriendSuggestions(String orderBy, String sortBy, Integer page, Integer pageSize) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.getFriendSuggestions(username, getPage(orderBy, sortBy, page, pageSize))
				.map(u -> new UserResponse(u));
	}

	private Pageable getPage(String orderBy, String sortBy, Integer page, Integer pageSize) {
		if (page == null) {
			page = 0;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (!StringUtils.hasLength(orderBy)) {
			orderBy = "first_name";
		}
		Direction direction = "desc".equals(sortBy) ? Direction.DESC : Direction.ASC;
		return PageRequest.of(page, pageSize, direction, orderBy);
	}

}
