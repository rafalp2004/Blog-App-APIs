package com.blog_rest_app.dto.user;

public record UserDTO(
        int id,
        String firstName,
        String lastName,
        String email

) {
}
