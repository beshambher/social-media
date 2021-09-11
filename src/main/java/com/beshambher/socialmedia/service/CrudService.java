package com.beshambher.socialmedia.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.beshambher.socialmedia.domain.oauth.CustomOAuth2User;
import com.beshambher.socialmedia.domain.oauth.CustomOidcUser;
import com.beshambher.socialmedia.entity.user.User;

public abstract interface CrudService<T> {

	String defaultOrder();

	String defaultSort();

	default String getUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	default User getUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal instanceof CustomOidcUser ? ((CustomOidcUser) principal).getUser()
				: ((CustomOAuth2User) principal).getUser();
	}

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

	<E extends T> E create(E entity);

	<E extends T> E update(E entity);

	void deleteById(String id);

}
