package com.beshambher.socialmedia.service.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.beshambher.socialmedia.domain.oauth.CustomOidcUser;
import com.beshambher.socialmedia.entity.user.User;
import com.beshambher.socialmedia.exception.OAuth2AuthenticationProcessingException;
import com.beshambher.socialmedia.service.UserService;

@Service
public class CustomOidcUserDetailsService extends OidcUserService {

	@Autowired
	private UserService userService;

	@Override
	public OidcUser loadUser(OidcUserRequest oidcUserRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = super.loadUser(oidcUserRequest);
		try {
			return getCustomOAuth2User(oidcUserRequest, oidcUser);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			// Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OidcUser getCustomOAuth2User(OAuth2UserRequest oAuth2UserRequest, OidcUser oidcUser) {

		if (((String) oidcUser.getAttribute("email")).isEmpty()) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		User user = userService.processOAuthUser(oidcUser);
		return new CustomOidcUser(user, oidcUser);
	}

}
