package com.projet.korector.repository;

import com.projet.korector.entity.CriteriaGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaGroupRepository extends JpaRepository<CriteriaGroup,Long> {
}
