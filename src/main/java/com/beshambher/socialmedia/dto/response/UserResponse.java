package com.beshambher.socialmedia.dto.response;

import com.beshambher.socialmedia.entity.user.User;

public record UserResponse(
        String username,
        String firstName,
        String lastName,
        String avatar
) {
    public UserResponse(User user) {
        this(user.getUsername(), user.getFirstName(), user.getLastName(), user.getAvatar());
    }
}
