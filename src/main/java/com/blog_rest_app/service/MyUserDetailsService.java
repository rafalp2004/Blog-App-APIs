package com.blog_rest_app.service;

import com.blog_rest_app.entity.Role;
import com.blog_rest_app.entity.User;
import com.blog_rest_app.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).
                orElseThrow(() ->
                        new UsernameNotFoundException("User with email: " + email + " not found"));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapAuthorities(user.getRoles())
        );

    }

    private Collection<? extends GrantedAuthority> mapAuthorities(List<Role> roles) {
        return roles.stream()
                .map((r) -> new SimpleGrantedAuthority(r.getName()))
                .toList();
    }
}
