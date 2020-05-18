package com.projet.korector.services;

import com.projet.korector.Exceptions.ResourceAlreadyExistsException;
import com.projet.korector.Exceptions.ResourceNotFoundException;
import com.projet.korector.entity.Criteria;
import com.projet.korector.repository.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CriteriaService {

    @Autowired
    private CriteriaRepository criteriaRepository;


    public Criteria createCriteria(Criteria criteriaImp) throws ResourceAlreadyExistsException {
        if(criteriaRepository.existsByName(criteriaImp.getName()))  {
           throw new ResourceAlreadyExistsException("Resource already exist");
        }else {
            Criteria criteria= new Criteria();
            if (criteriaImp.getType().equals("Statique")) {

                criteria = criteriaRepository.save(new Criteria(criteriaImp.getName(), criteriaImp.getType(), criteriaImp.getUrl()));
            } else if (criteriaImp.getType().equals("Dynamique")) {
                criteria = criteriaRepository.save(new Criteria(criteriaImp.getName(), criteriaImp.getType(), ""));

            }
            System.out.println("L'ajout du critere à été effectué avec succès!");
            return criteria;
        }
    }

    public ResponseEntity<Criteria> updateCriteria(Long id, Criteria criteria)
    {
        Optional<Criteria> criteriaData = criteriaRepository.findById(id);

        if (criteriaData.isPresent()) {
            Criteria _criteria = criteriaData.get();
            _criteria.setName(criteria.getName());
            _criteria.setType(criteria.getType());
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

    public List<Criteria> researchCriteria(String name, String type) throws ResourceNotFoundException {
        List<Criteria> criteria =  criteriaRepository.findByNameAndType(name,type);
        if (!criteria.isEmpty()) {
            return criteria;
        }else {
            throw new ResourceNotFoundException("Resource Not found!");
        }
    }

    public List researchCriteriaByName(String name) throws ResourceNotFoundException {
        List criteria =  criteriaRepository.findByName(name);
        if (!criteria.isEmpty()) {
            return criteria;
        }else  {
            throw  new ResourceNotFoundException("Resource Not found!");
        }
    }

    public List<Criteria> researchCriteriaByType(String type) throws ResourceNotFoundException {
        List<Criteria> criteria =  criteriaRepository.findByType(type);
        if (!criteria.isEmpty()) {
            return criteria;
        } else {
            throw  new ResourceNotFoundException("Resource Not found!");
        }
    }
}