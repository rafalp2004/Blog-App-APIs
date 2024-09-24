package com.blog_rest_app.dto.post;

public record UpdatePostDTO(
        int id,
        String title,
        String content,
        String category
) {
}
