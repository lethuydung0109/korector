package com.projet.korector.services;

import com.projet.korector.Exceptions.ResourceNotFoundException;
import com.projet.korector.entity.Criteria;
import com.projet.korector.model.CriteriaImpl;
import com.projet.korector.model.DynamicCriteriaImpl;
import com.projet.korector.model.StaticCriteriaImpl;
import com.projet.korector.repository.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.servlet.Registration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;


    public Criteria createCriteria(Criteria criteriaImp)
    {
        Criteria criteria= new Criteria();
        if(criteriaImp.getType().equals("Statique")) {
            criteria= criteriaRepository.save(new Criteria(criteriaImp.getName(),criteriaImp.getType(),criteriaImp.getUrl(),criteriaImp.getValue()));
        }else if(criteriaImp.getType().equals("Dynamique")) {

            criteria= criteriaRepository.save(new Criteria(criteriaImp.getName(),criteriaImp.getType(),"",criteriaImp.getValue()));
        }
        System.out.println("L'ajout du critere à été effectué avec succès!");
        return criteria;
    }

    public ResponseEntity<Criteria> updateCriteria(Long id, Criteria criteria)
    {
        Optional<Criteria> criteriaData = criteriaRepository.findById(id);

        if (criteriaData.isPresent()) {
            Criteria _criteria = criteriaData.get();
            _criteria.setName(criteria.getName());
            _criteria.setType(criteria.getType());
            _criteria.setValue(criteria.getValue());
            _criteria.setUrl(criteria.getUrl());
            System.out.println("La mise à jours du critère a bien été prise en compte!");
            return new ResponseEntity<>(criteriaRepository.save(_criteria), HttpStatus.OK);
        } else {
            System.out.println("Erreur lors de la mise à jours du critère !");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public List<Criteria> getAllCriteria()
    {
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaRepository.findAll().forEach(criteriaList::add);
        return criteriaList;
    }

    public ResponseEntity<String> deleteCriteria(Long id)
    {
        criteriaRepository.deleteById(id);
        return  new ResponseEntity<>("Le critère a été supprimé avec succès",HttpStatus.OK);
    }

    public ResponseEntity<Criteria> getCriteriaById(Long id) throws ResourceNotFoundException {

            Criteria criteria = criteriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Criteria not found for this id :: " + id));
            return ResponseEntity.ok().body(criteria);

    }

    public List<Criteria> researchCriteria(String name, String type) {
        List<Criteria> criteria =  criteriaRepository.findByNameAndType(name,type);
        if (!criteria.isEmpty()) {
            return criteria;
        }else {
            return null;
        }
    }

    public List <Criteria> researchCriteriaByName(String name) {
        List criteria =  criteriaRepository.findByName(name);
        if (!criteria.isEmpty()) {
            return criteria;
        }else {
            return null;
        }

    }

    public List<Criteria> researchCriteriaByType(String type) {
        List<Criteria> criteria =  criteriaRepository.findByType(type);
        if (!criteria.isEmpty()) {
            return criteria;
        }else {
            return null;
        }
    }
}
