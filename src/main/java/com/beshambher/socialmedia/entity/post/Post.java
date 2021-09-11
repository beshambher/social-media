package com.beshambher.socialmedia.entity.post;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.beshambher.socialmedia.entity.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", length = 50)
	private String id;

	@Lob
	@Column(name = "body", length = 4096, nullable = false)
	private String body;

	@Column(name = "likes", columnDefinition = "integer default 0")
	private Integer likes;

	@Column(name = "comments_count", columnDefinition = "integer default 0")
	private Integer commentsCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	private User user;

	@Column(name = "username", insertable = false, updatable = false, nullable = false)
	private String username;

	@ManyToMany
	@JoinTable(name = "post_likes", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "username"))
	List<User> postLikes;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	@Override
	public String toString() {
		return "Post [id=" + id + ", body=" + body + ", likes=" + likes + ", commentsCount=" + commentsCount + ", user="
				+ user + ", username=" + username + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
