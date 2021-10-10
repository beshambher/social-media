package com.beshambher.socialmedia.service;

public abstract interface CrudService<T> extends BaseService {

	T create(T entity) throws Exception;

	T update(T entity, String id) throws Exception;

	void deleteById(String id) throws Exception;

	T findById(String id) throws Exception;
}
