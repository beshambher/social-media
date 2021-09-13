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

	T create(T entity);

	T update(T entity, String id) throws Exception;

	void deleteById(String id) throws Exception;

	T findById(String id) throws Exception;
}
