package com.blog_rest_app.controller;

import com.blog_rest_app.dto.user.CreateUserDTO;
import com.blog_rest_app.dto.user.UpdateUserDTO;
import com.blog_rest_app.dto.user.UserDTO;
import com.blog_rest_app.service.RoleService;
import com.blog_rest_app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
    }


    @GetMapping(path="/users")
    public List<UserDTO> getUsers(){
        List<UserDTO> users = userService.findAll();
        return users;
    }

    @GetMapping(path="/users/{id}")
    public UserDTO getUserById(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping(path="/users")
    public CreateUserDTO createUser(@RequestBody CreateUserDTO userDTO){
        userService.save(userDTO);
        return userDTO;

    }

    @DeleteMapping(path="/users/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteById(id);
    }

    @PutMapping(path = "/users")
    public UpdateUserDTO updateUser(@RequestBody UpdateUserDTO userDTO){
        return userService.update(userDTO);
    }

}
