package com.projet.korector.controller;


import com.projet.korector.entity.AuthProvider;
import com.projet.korector.entity.ERole;
import com.projet.korector.entity.Role;
import com.projet.korector.entity.User;
import com.projet.korector.payload.request.LoginRequest;
import com.projet.korector.payload.request.SignupRequest;
import com.projet.korector.payload.response.JwtResponse;
import com.projet.korector.payload.response.MessageResponse;
import com.projet.korector.repository.RoleRepository;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.security.jwt.JwtUtils;
import com.projet.korector.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role etudiantRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)

                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(etudiantRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "enseignant":
                        Role enseignantRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(enseignantRole);

                        break;
                    default:
                        Role etudiantRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(etudiantRole);
                }
            });
        }

        user.setRoles(roles);
        user.setProvider(AuthProvider.local);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
