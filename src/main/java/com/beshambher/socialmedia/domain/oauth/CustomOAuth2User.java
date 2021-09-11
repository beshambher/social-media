package com.beshambher.socialmedia.domain.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.beshambher.socialmedia.constants.Constant;
import com.beshambher.socialmedia.entity.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomOAuth2User implements OAuth2User {

	private User user;
	private String email;
	private String username;
	private Map<String, Object> attributes;
	private String role = Constant.Role.ROLE_USER.toString();
	private Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);

	public CustomOAuth2User(User user, OAuth2User oAuth2User) {
		this.user = user;
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.role = user.getRole().getName();
		this.attributes = oAuth2User.getAttributes();
		this.authorities = oAuth2User.getAuthorities();
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getName() {
		return this.username;
	}

}
