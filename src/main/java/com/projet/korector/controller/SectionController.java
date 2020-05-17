package com.projet.korector.controller;
import com.projet.korector.entity.Section;
import com.projet.korector.services.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/")
public class SectionController {

    final static Logger log = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    private SectionService service;

    @PostMapping(value = "/createSection" )
    public String createSection(@RequestBody Section section) {
        System.out.println("Création de la section"+ section.toString());
        String response ="ERROR !!";
        if(service.existByName(section.getName())){
            response = "This section name already exist ";
        }
        else{
            service.createSection(section);
            response = "The section is created successfully";
        }

        return response;
    }

    @RequestMapping(value = "/allSections", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Section> getAllSections() { return service.getAllSections(); }

    @DeleteMapping(value = "/deleteSection/{sectionId}")
    public void deleteSection(@PathVariable Long sectionId)
    {
        service.deleteSection(sectionId);
    }

    @RequestMapping(value = "/SectionByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Section getSectionByName(@PathVariable String name)
    {
        return service.getSectionByName(name);
    }

    @RequestMapping(value = "/SectionById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Section getSectionById(@PathVariable Long id)
    {
        return service.getSectionById(id);
    }



}
