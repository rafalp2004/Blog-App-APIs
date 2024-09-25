package com.blog_rest_app.dto.comment;


public record CommentDTO(
        int id,
        String content,
        String user
) {
}
