package com.beshambher.socialmedia.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.beshambher.socialmedia.constants.Constant;
import com.beshambher.socialmedia.domain.oauth.CustomOAuth2User;
import com.beshambher.socialmedia.domain.oauth.CustomOidcUser;
import com.beshambher.socialmedia.domain.response.SessionUserResponse;
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
	public User processOAuthUser(OAuth2User oAuthUser) {
		User user = userRepository.findByEmail((String) oAuthUser.getAttribute("email"));

		if (user == null) {
			Role defaultRole = roleRepository.findByName(Constant.Role.ROLE_USER.toString());
			user = new User();
			user.setRole(defaultRole);
		}
		
		Map<String, Object> userAttributes = oAuthUser.getAttributes();
		String firstName = (String) userAttributes.get("name");
		if (firstName.indexOf(' ') > 0) {
			user.setLastName(firstName.substring(firstName.indexOf(' ') + 1));
			firstName = firstName.substring(0, firstName.indexOf(' '));
		}
		user.setFirstName(firstName);
		user.setEmail((String) userAttributes.get("email"));
		user.setLocation((String) userAttributes.get("location"));
		user.setAvatar((String) userAttributes.getOrDefault("avatar_url", "") + (String) userAttributes.getOrDefault("picture", ""));
		user = userRepository.save(user);

		return user;
	}

	@Override
	public SessionUserResponse getSessionUser(OAuth2User authenticationUser) {
		if (authenticationUser instanceof CustomOidcUser) {
			return new SessionUserResponse((CustomOidcUser) authenticationUser);
		}
		return new SessionUserResponse((CustomOAuth2User) authenticationUser);
	}

}
