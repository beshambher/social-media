package com.beshambher.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.authority.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

	Role findByName(String name);

}
