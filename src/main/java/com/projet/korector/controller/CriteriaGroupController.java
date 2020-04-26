package com.projet.korector.controller;


import com.projet.korector.entity.CriteriaGroup;
import com.projet.korector.entity.CriteriaGroupImpl;
import com.projet.korector.services.CriteriaGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/CriteriaGroup")
public class CriteriaGroupController {
    @Autowired
    private CriteriaGroupService service;

    @PostMapping("/createCriteriaGroup")
    public CriteriaGroup createCriteriaGroup(CriteriaGroupImpl criteriaGImp)
    {
        return service.createCriteriaGroup(criteriaGImp);
    }

    @PutMapping("/updateCriteriaGroup/{id}")
    public void updateCriteriaGroup(@PathVariable Long id, @RequestBody CriteriaGroup criteriaG)
    {
        service.updateCriteriaGroup(id,criteriaG);
    }

    @GetMapping("/allCriteriaGroup")
    public List<CriteriaGroup> getAllCriteriaGroup()
    {
        return service.getAllCriteriaGroup();
    }

    @DeleteMapping("/deleteCriteriaGroup/{id}")
    public void deleteCriteriaGroup(@PathVariable Long id)
    {
        service.deleteCriteriaGroup(id);
    }

    @GetMapping("/researchCriteriaGroup")
    public ResponseEntity<CriteriaGroup> researchCriteriaGroup(CriteriaGroupImpl criteriaGroup)
    {
        return service.researchCriteriaGroup(criteriaGroup);
    }


}
