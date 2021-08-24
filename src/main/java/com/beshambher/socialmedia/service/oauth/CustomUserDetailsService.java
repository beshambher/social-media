package com.beshambher.socialmedia.service.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.beshambher.socialmedia.domain.oauth.CustomOAuth2User;
import com.beshambher.socialmedia.entity.user.User;
import com.beshambher.socialmedia.exception.OAuth2AuthenticationProcessingException;
import com.beshambher.socialmedia.service.UserService;

@Service
public class CustomUserDetailsService extends DefaultOAuth2UserService {

	@Autowired
	private UserService userService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		try {
			return getCustomOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			// Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OAuth2User getCustomOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		
		if(((String) oAuth2User.getAttribute("email")).isEmpty()) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
		User user = userService.processOAuthUser(oAuth2User);
		return new CustomOAuth2User(user, oAuth2User);
	}

}
