package com.beshambher.socialmedia.entity.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.beshambher.socialmedia.entity.authority.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "username", length = 100, nullable = false, unique = true)
	private String username;

	@Column(name = "first_name", length = 20, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 30)
	private String lastName;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "avatar", nullable = true)
	private String avatar;

	@Column(name = "location", nullable = true)
	private String location;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserFriend> friends = new HashSet<>();

}
