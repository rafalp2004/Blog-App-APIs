package com.blog_rest_app.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
        @NotNull
        int id,

        @NotBlank
        @Size(min=2, max=50, message="First name must be between 2 and 50 characters")
        String firstName,

        @NotBlank
        @Size(min=2, max=50, message="Last name must be between 2 and 50 characters")
        String lastName,

        @Email
        String email
        ) {
}
