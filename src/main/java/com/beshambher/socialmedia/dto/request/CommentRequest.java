package com.beshambher.socialmedia.dto.request;

import jakarta.validation.constraints.Size;

public record CommentRequest(
        String postId,

        @Size(max = 255, message = "Comment cannot exceed 255 characters")
        String comment
) {
}
