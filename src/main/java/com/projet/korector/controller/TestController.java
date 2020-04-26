package com.projet.korector.controller;

import com.projet.korector.exception.ResourceNotFoundException;
import com.projet.korector.entity.Role;
import com.projet.korector.entity.User;

import com.projet.korector.repository.RoleRepository;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.security.CurrentUser;
import com.projet.korector.security.services.UserDetailsImpl;
import com.projet.korector.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;
    Set<Role> roles = new HashSet<>();

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    private AuthenticationManager authenticationManager;

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserDetailsImpl userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
