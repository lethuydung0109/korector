package com.projet.korector.repository;

import java.util.Optional;

import com.projet.korector.model.ERole;
import com.projet.korector.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}