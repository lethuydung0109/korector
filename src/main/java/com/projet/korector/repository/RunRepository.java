package com.projet.korector.repository;

import com.projet.korector.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunRepository extends JpaRepository<Session,Long> {
}
