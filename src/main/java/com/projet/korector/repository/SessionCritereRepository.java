package com.projet.korector.repository;

import com.projet.korector.entity.SessionCritere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionCritereRepository extends JpaRepository<SessionCritere,Long> {
}
