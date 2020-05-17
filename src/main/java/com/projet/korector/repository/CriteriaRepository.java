package com.projet.korector.repository;

import com.projet.korector.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria,Long> {
    List<Criteria> findByNameAndType(String name, String type);

    List<Criteria> findByName(String name);

    List<Criteria> findByType(String type);

    boolean existsByName(String name);
}
