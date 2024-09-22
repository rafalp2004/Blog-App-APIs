package com.blog_rest_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(path="/users")
    public String getUsers(){
        return "Hello World";
    }
}
