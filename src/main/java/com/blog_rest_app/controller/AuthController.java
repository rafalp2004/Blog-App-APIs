package com.blog_rest_app.controller;

import com.blog_rest_app.dto.user.LoginUserDTO;
import com.blog_rest_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginUserDTO userDTO) {

        return userService.verify(userDTO);
    }
}
