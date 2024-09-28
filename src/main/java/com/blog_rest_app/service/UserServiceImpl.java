package com.blog_rest_app.service;

import com.blog_rest_app.dto.user.CreateUserDTO;
import com.blog_rest_app.dto.user.LoginUserDTO;
import com.blog_rest_app.dto.user.UpdateUserDTO;
import com.blog_rest_app.dto.user.UserDTO;
import com.blog_rest_app.entity.User;
import com.blog_rest_app.exception.ResourceNotFoundException;
import com.blog_rest_app.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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
    public void save(CreateUserDTO userDTO) {
        User tempUser = new User();
        tempUser.setFirstName(userDTO.firstName());
        tempUser.setLastName(userDTO.lastName());
        tempUser.setEmail(userDTO.email());

        tempUser.setPassword(encoder.encode(userDTO.password()));
        tempUser.addRole(roleService.findByName("ROLE_USER"));
        userRepository.save(tempUser);
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

    @Override
    public String verify(LoginUserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userDTO.email());
        }
        throw new BadCredentialsException("Invalid credentials");

    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

}
