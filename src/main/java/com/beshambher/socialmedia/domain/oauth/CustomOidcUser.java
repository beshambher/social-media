package com.beshambher.socialmedia.domain.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.beshambher.socialmedia.constants.Constant;
import com.beshambher.socialmedia.entity.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomOidcUser extends DefaultOidcUser {

	private static final long serialVersionUID = 1L;

	private User user;
	private String email;
	private String username;
	private Map<String, Object> attributes;
	private String role = Constant.Role.USER.toString();
	private Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);

	public CustomOidcUser(User user, OidcUser oidcUser) {
		super(oidcUser.getAuthorities(), oidcUser.getIdToken(), oidcUser.getUserInfo());
		this.user = user;
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.role = user.getRole().getName();
		this.attributes = oidcUser.getAttributes();
		this.authorities = oidcUser.getAuthorities();
	}

	@Override
	public String getName() {
		return this.username;
	}

	@Override
	public Map<String, Object> getClaims() {
		return super.getClaims();
	}

}
