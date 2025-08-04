package com.beshambher.socialmedia.entity.post;

import com.beshambher.socialmedia.entity.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @UuidGenerator
    @Column(name = "id", length = 36)
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostLike> postLikes = new HashSet<>();

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
