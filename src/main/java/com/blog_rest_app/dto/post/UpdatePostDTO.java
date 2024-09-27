package com.blog_rest_app.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePostDTO(
        @NotNull
        int id,

        @NotBlank
        @Size(min = 2, max = 255, message = "Title must be between 2 and 30 characters")
        String title,

        @NotBlank
        @Size(min = 2, max = 255, message = "Comment must be between 2 and 1000  characters")
        String content,

        @NotBlank
        String category
) {
}
