package com.beshambher.socialmedia.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.beshambher.socialmedia.entity.authority.Role;

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
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", length = 36)
	private String id;

	@Column(name = "first_name", length = 20, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 30)
	private String lastName;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "avatar", nullable = true)
	private String avatar;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	@ManyToOne
	private Role role;

}
