package com.blog_rest_app.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCommentDTO(
        @NotBlank
        @Size(min=2, max=255, message="Comment must be between 2 and 255 characters")
        String content
) {

}
