package com.projet.korector.repository;

import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.entity.SonarResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SonarResultsRepository extends JpaRepository<SonarResults,Long> {

   List<SonarResults> findBySessionIdAndProjectId(Long sessionId, Long projectId);
   Boolean existsBySessionId(Long sessionId);
   Boolean existsBySessionIdAndProjectId(Long sessionId, Long projectId);


}
