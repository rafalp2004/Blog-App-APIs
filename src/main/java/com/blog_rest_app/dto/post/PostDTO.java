package com.blog_rest_app.dto.post;

import com.blog_rest_app.dto.comment.CommentDTO;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public record PostDTO(
        @NotBlank int id,
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank LocalDateTime dateTime,
        @NotBlank String category,
        @NotBlank List<CommentDTO> comments,
        @NotBlank String user
)

{

}
