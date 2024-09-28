package com.blog_rest_app.controller;

import com.blog_rest_app.dto.user.CreateUserDTO;
import com.blog_rest_app.dto.user.UpdateUserDTO;
import com.blog_rest_app.dto.user.UserDTO;
import com.blog_rest_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/users")
    public ResponseEntity<List<UserDTO>> findUsers() {
        List<UserDTO> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable int id) {
        UserDTO userDTO = userService.findById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody CreateUserDTO userDTO) {
        userService.save(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping(path = "/users")
    public ResponseEntity<UpdateUserDTO> updateUser(@Valid @RequestBody UpdateUserDTO userDTO) {
        UpdateUserDTO updatedUserDTO = userService.update(userDTO);
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

}
