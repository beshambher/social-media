package com.beshambher.socialmedia.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

import com.beshambher.socialmedia.constants.Constant.Client;
import com.beshambher.socialmedia.constants.Constant.Role;
import com.beshambher.socialmedia.domain.oauth.CustomOAuth2User;
import com.beshambher.socialmedia.domain.oauth.CustomOidcUser;
import com.beshambher.socialmedia.entity.user.User;

public interface BaseService {

	String defaultOrder();

	String defaultSort();

	default Pageable getPage(String orderBy, String sortBy, Integer page, Integer pageSize) {
		if (page == null) {
			page = 0;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (!StringUtils.hasLength(orderBy)) {
			orderBy = this.defaultOrder();
		}
		if (!StringUtils.hasLength(sortBy)) {
			sortBy = this.defaultSort();
		}
		Direction direction = "desc".equals(sortBy) ? Direction.DESC : Direction.ASC;
		return PageRequest.of(page, pageSize, direction, orderBy);
	}

	default String getUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	default User getUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal instanceof CustomOidcUser ? ((CustomOidcUser) principal).getUser()
				: ((CustomOAuth2User) principal).getUser();
	}

	default String getRole() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			try {
				String clientId = null;
				if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
					clientId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
				}
				if (Client.GITHUB.equals(clientId) && authentication.getPrincipal() instanceof OAuth2User) {
					CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
					return user.getRole();
				}
				if (Client.GOOGLE.equals(clientId) && authentication.getPrincipal() instanceof OidcUser) {
					CustomOidcUser user = (CustomOidcUser) authentication.getPrincipal();
					return user.getRole();
				}
			} catch (Exception e) {
				System.err.println("Error at getRole(): " + e.getLocalizedMessage());
			}
		}
		return null;
	}

	default boolean isAdmin() {
		return Role.ADMIN.toString().equals(getRole());
	}

}
