package com.projet.korector;

import com.projet.korector.model.AuthProvider;
import com.projet.korector.model.ERole;
import com.projet.korector.model.Role;
import com.projet.korector.model.User;
import com.projet.korector.repository.RoleRepository;
import com.projet.korector.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("DB initializes...");
        List<Role> roles = roleRepository.findAll();
        List<User> users = userRepository.findAll();

        if(roles.isEmpty()){
            roleRepository.save(new Role(ERole.ROLE_ETUDIANT));
            roleRepository.save(new Role(ERole.ROLE_ENSEIGNANT));
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
            System.out.println("--- Roles initialized");

        }

        if(users.isEmpty()){
            User admin = new User(
                    "admin", "admin@korector.com",
                    encoder.encode("adminkorector"),
                    "Admin", "https://www.pngitem.com/pimgs/m/226-2260470_transparent-admin-icon-png-admin-logo-png-png.png"
            );
            Set<Role> newRole = new HashSet<>();
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            System.out.println("Role = " + adminRole.toString());
            newRole.add(adminRole);
            admin.setRoles(newRole);
            admin.setProvider(AuthProvider.local);

            userRepository.save(admin);

            System.out.println("--- Admin user initialized");
        }


    }
}
