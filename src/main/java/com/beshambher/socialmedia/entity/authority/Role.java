package com.beshambher.socialmedia.entity.authority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @UuidGenerator
    @Column(name = "id", length = 36)
    private String id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "boolean default true")
    private Boolean isEditable;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
