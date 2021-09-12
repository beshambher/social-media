package com.beshambher.socialmedia.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

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

}
