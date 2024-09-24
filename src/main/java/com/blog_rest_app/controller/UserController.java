package com.blog_rest_app.controller;

import com.blog_rest_app.entity.User;
import com.blog_rest_app.service.RoleService;
import com.blog_rest_app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(path="/users")
    public List<User> getUsers(){
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping(path="/users/{id}")
    public User getUserById(@PathVariable int id){
        User user = userService.findById(id);
        return user;
    }

    @PostMapping(path="/users")
    public User createUser(@RequestBody User user){
        user.addRole(roleService.findByName("User"));
        user.setId(0);
        User dbUser = userService.save(user);
        return dbUser;
    }

    @DeleteMapping(path="/users/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteById(id);
    }

    @PutMapping(path = "/users")
    public void updateUser(@RequestBody User user){
        User tempUser = userService.findById(user.getId());
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setEmail(user.getEmail());
        //Current version of changing password. Later it will be correct
        tempUser.setPassword(user.getPassword());
        userService.save(tempUser);

    }

}
