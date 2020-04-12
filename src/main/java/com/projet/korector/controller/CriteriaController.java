package com.projet.korector.controller;

import com.projet.korector.entity.Criteria;
import com.projet.korector.model.CriteriaImpl;

import com.projet.korector.model.DynamicCriteriaImpl;
import com.projet.korector.model.StaticCriteriaImpl;
import com.projet.korector.services.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/Criteria")
public class CriteriaController {

    @Autowired
    private CriteriaService service;

    @PostMapping("/createCriteria")
    public Criteria createCriteria(CriteriaImpl criteriaImp)
    {
        if(criteriaImp instanceof StaticCriteriaImpl){
            return service.createCriteria((StaticCriteriaImpl)criteriaImp);
        }else {
            return service.createCriteria((DynamicCriteriaImpl)criteriaImp);
        }

    }

    @PutMapping("/updateCriteria/{id}")
    public ResponseEntity<Criteria> updateCriteria(@PathVariable Long id, @RequestBody Criteria criteria)
    {
        return service.updateCriteria(id,criteria);
    }

    @GetMapping("/allCriteria")
    public List<Criteria> getAllCriteria()
    {
        return service.getAllCriteria();
    }

    @DeleteMapping("/deleteCriteria/{id}")
    public void deleteCriteria(@PathVariable Long id)
    {
        service.deleteCriteria(id);
    }

    @GetMapping("/researchCriteria/{id}")
    public ResponseEntity<Criteria> researchCriteria(@PathVariable Long id)
    {
       return service.researchCriteria(id);
    }

}
