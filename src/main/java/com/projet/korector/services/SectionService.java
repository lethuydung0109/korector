package com.projet.korector.services;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Section;
import com.projet.korector.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    public Section createSection(Section section) {
        return null;
    }

    public List<Section> getAllSections() { return repository.findAll(); }

    public void deleteSection(Long sectionId) {
        repository.deleteById(sectionId);
    }

    public Section getSectionByName(String name) {
        return repository.getSectionByName(name);
    }

    public Section getSectionById(Long id) {
        return repository.findSectionById(id);
    }
    public boolean existByName(String name) {
        return repository.existsByName(name);
    }

}
