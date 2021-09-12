package com.beshambher.socialmedia.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.beshambher.socialmedia.domain.oauth.CustomOAuth2User;
import com.beshambher.socialmedia.domain.oauth.CustomOidcUser;
import com.beshambher.socialmedia.entity.user.User;

public abstract interface CrudService<T> extends BaseService {

	default User getUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal instanceof CustomOidcUser ? ((CustomOidcUser) principal).getUser()
				: ((CustomOAuth2User) principal).getUser();
	}

	<E extends T> E create(E entity);

	<E extends T> E update(E entity);

	void deleteById(String id);

}
