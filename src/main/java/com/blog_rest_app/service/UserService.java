package com.blog_rest_app.service;

import com.blog_rest_app.dto.user.CreateUserDTO;
import com.blog_rest_app.dto.user.LoginUserDTO;
import com.blog_rest_app.dto.user.UpdateUserDTO;
import com.blog_rest_app.dto.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  {
    List<UserDTO> findAll();

    UserDTO findById(int id);

    void save(CreateUserDTO userDTO);

    UpdateUserDTO update(UpdateUserDTO userDTO);

    void deleteById(int id);

    String verify(LoginUserDTO userDTO);
}
