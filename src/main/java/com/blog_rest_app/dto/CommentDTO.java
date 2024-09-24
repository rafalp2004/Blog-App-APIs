package com.blog_rest_app.dto;

public record CommentDTO(
        int id,
        String content,
        String user
) {
}
