package com.beshambher.socialmedia.domain.oauth;

import com.beshambher.socialmedia.constants.Constant;
import com.beshambher.socialmedia.entity.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOidcUser extends DefaultOidcUser {

	private final User user;
	private final String email;
	private final String username;
	private final String location;
	private final String lastName;
	private final String firstName;
	private Map<String, Object> attributes;
	private String role = Constant.Role.USER.toString();
	private Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);

	public CustomOidcUser(User user, OidcUser oidcUser) {
		super(oidcUser.getAuthorities(), oidcUser.getIdToken(), oidcUser.getUserInfo());
		this.user = user;
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.location = user.getLocation();
		this.lastName = user.getLastName();
		this.firstName = user.getFirstName();
		this.role = user.getRole().getName();
		this.attributes = oidcUser.getAttributes();
		this.authorities = oidcUser.getAuthorities();
	}

	@Override
	public Map<String, Object> getAttributes() {
		return super.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return super.getAuthorities();
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
