package com.projet.korector.repository;

import com.projet.korector.entity.Project;
import com.projet.korector.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository  extends JpaRepository<Project,Long> {


    Section getSectionByName(Long name);

    Section findSectionById(Long id);
}