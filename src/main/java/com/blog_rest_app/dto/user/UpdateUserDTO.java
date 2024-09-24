package com.blog_rest_app.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
        @NotBlank int id,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email String email
        ) {
}
