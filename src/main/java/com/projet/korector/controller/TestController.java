package com.projet.korector.controller;

import com.projet.korector.entity.Session;
import com.projet.korector.exception.ResourceNotFoundException;
import com.projet.korector.model.Role;
import com.projet.korector.model.User;
import com.projet.korector.model.UserDTO;
import com.projet.korector.payload.response.MessageResponse;
import com.projet.korector.repository.RoleRepository;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.security.CurrentUser;
import com.projet.korector.security.services.UserDetailsImpl;
import com.projet.korector.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
