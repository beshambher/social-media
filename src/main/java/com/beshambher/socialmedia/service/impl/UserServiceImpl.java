package com.beshambher.socialmedia.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.beshambher.socialmedia.constants.Constant;
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

	public User sessionUser(OAuth2User oauthUser) {
		User user = userRepository.findByEmail((String) oauthUser.getAttribute("email"));

		if (user == null) {
			Role defaultRole = roleRepository.findByName(Constant.Role.ROLE_USER.toString());
			Map<String, Object> userAttributes = oauthUser.getAttributes();
			user = new User();
			user.setRole(defaultRole);
			String firstName = (String) userAttributes.get("name");
			if (firstName.indexOf(' ') > 0) {
				user.setLastName(firstName.substring(firstName.indexOf(' ') + 1));
				firstName = firstName.substring(0, firstName.indexOf(' '));
			}
			user.setFirstName(firstName);
			user.setEmail((String) userAttributes.get("email"));
			user.setAvatar((String) userAttributes.getOrDefault("avatar_url", "") + (String) userAttributes.getOrDefault("picture", ""));
			user = userRepository.save(user);
		}

		return user;
	}

}
