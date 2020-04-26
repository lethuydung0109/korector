package com.projet.korector.services;

import com.projet.korector.entity.CriteriaGroup;
import com.projet.korector.entity.CriteriaGroupImpl;
import com.projet.korector.repository.CriteriaGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CriteriaGroupService {
    @Autowired
    private CriteriaGroupRepository criteriaGroupRepository;

    public CriteriaGroup createCriteriaGroup(CriteriaGroupImpl criteriaGroup) {
        CriteriaGroup cg =  criteriaGroupRepository.save(new CriteriaGroup(criteriaGroup.getId(),criteriaGroup.getName()));
        System.out.println("L'ajout du groupe de critere "+criteriaGroup.getName()+"à été effectué avec succès!");
        return  cg;
    }

    public  void updateCriteriaGroup(Long id, CriteriaGroup criteriaGroup) {
        Optional<CriteriaGroup> criteriaGroupData = criteriaGroupRepository.findById(id);

        if(criteriaGroupData.isPresent()) {
            CriteriaGroup cg = criteriaGroupData.get();
            cg.setName(criteriaGroup.getName());
            System.out.println("La mise à jour du groupe de critère a bien été prise en compte!");
        }else {
            System.out.println("Erreur lors de a mise à jours du critère!");
        }
    }

    public List<CriteriaGroup> getAllCriteriaGroup()
    {
        List<CriteriaGroup> criteriaGroupList = new ArrayList<>();
        criteriaGroupRepository.findAll().forEach(criteriaGroupList::add);
        return criteriaGroupList;
    }

    public ResponseEntity<String> deleteCriteriaGroup(Long id)
    {
        criteriaGroupRepository.deleteById(id);
        return  new ResponseEntity<>("Le Groupe de critères a été supprimé avec succès", HttpStatus.OK);
    }
    public ResponseEntity<CriteriaGroup> researchCriteriaGroup(CriteriaGroupImpl  cg) {
        Optional<CriteriaGroup> criteriaGroup =  criteriaGroupRepository.findById(cg.getId());
        if (criteriaGroup.isPresent()) {
            return new ResponseEntity<>(criteriaGroup.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
