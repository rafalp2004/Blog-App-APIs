package com.blog_rest_app.service;

import com.blog_rest_app.dto.user.CreateUserDTO;
import com.blog_rest_app.dto.user.UpdateUserDTO;
import com.blog_rest_app.dto.user.UserDTO;
import com.blog_rest_app.entity.User;
import com.blog_rest_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<UserDTO> usersDTO = userRepository.findAll().stream().map(this::mapToDTO).toList();
        return usersDTO;
    }

    @Override
    public UserDTO findById(int id) {
        Optional<User> tempUser = userRepository.findById(id);
        if (tempUser.isPresent()) {
            User user = tempUser.get();
            UserDTO userDTO = mapToDTO(user);
            return userDTO;
        }
        return null;
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
        Optional<User> tempUser = userRepository.findById(userDTO.id());
        if (tempUser.isPresent()) {
            User user = tempUser.get();
            user.setFirstName(userDTO.firstName());
            user.setLastName(userDTO.lastName());
            user.setEmail(userDTO.email());
            userRepository.save(user);
            return userDTO;
        }

        return null;
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

}
