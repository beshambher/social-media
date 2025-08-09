package com.beshambher.socialmedia.dto.request;

public record CommentRequest(
        String postId,
        String comment
) {
}
