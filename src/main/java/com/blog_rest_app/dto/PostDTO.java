package com.blog_rest_app.dto;

import com.blog_rest_app.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

public record PostDTO(
        int id,
        String title,
        String content,
        LocalDateTime dateTime,
        String category,
        List<CommentDTO> comments,
        String user
)

{

}
