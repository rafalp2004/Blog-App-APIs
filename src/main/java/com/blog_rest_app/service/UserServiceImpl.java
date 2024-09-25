package com.blog_rest_app.service;

import com.blog_rest_app.dto.user.CreateUserDTO;
import com.blog_rest_app.dto.user.UpdateUserDTO;
import com.blog_rest_app.dto.user.UserDTO;
import com.blog_rest_app.entity.User;
import com.blog_rest_app.exception.ResourceNotFoundException;
import com.blog_rest_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public UserDTO findById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));

        return mapToDTO(user);
    }


    @Override
    public CreateUserDTO save(CreateUserDTO userDTO) {
        User tempUser = new User();
        tempUser.setFirstName(userDTO.firstName());
        tempUser.setLastName(userDTO.lastName());
        tempUser.setEmail(userDTO.email());
        tempUser.setPassword(userDTO.password());
        tempUser.addRole(roleService.findByName("User"));
        userRepository.save(tempUser);
        return userDTO;
    }

    @Override
    public UpdateUserDTO update(UpdateUserDTO userDTO) {
        User user = userRepository.findById(userDTO.id()).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userDTO.id() + " not found"));

        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        userRepository.save(user);
        return userDTO;

    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

}
