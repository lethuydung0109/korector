package com.projet.korector.repository;

import com.projet.korector.entity.Session;
import com.projet.korector.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    Optional<Session> findByName(String name);
    Boolean existsByName(String username);

}
