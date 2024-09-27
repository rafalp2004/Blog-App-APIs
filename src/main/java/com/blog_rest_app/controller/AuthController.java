package com.blog_rest_app.controller;

import com.blog_rest_app.dto.user.LoginUserDTO;
import com.blog_rest_app.entity.User;
import com.blog_rest_app.service.MyUserDetailsService;
import com.blog_rest_app.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginUserDTO userDTO) {

        return userService.verify(userDTO);
    }
}
