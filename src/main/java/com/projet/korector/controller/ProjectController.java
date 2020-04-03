package com.projet.korector.controller;

import com.projet.korector.entity.Project;
import com.projet.korector.entity.Project;
import com.projet.korector.model.ProjectImp;
import com.projet.korector.model.ProjectImp;
import com.projet.korector.services.ProjectService;
import com.projet.korector.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/project")
public class ProjectController {

    final static Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService service;

    @RequestMapping(value = "/createProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createProject(@Valid @RequestBody Project projet) {
         service.createProject(projet);
    }

    @RequestMapping(value = "/allProjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getAllSections() { return service.getAllProjects(); }

    @RequestMapping(value = "/projectById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getProjectByUser(@PathVariable Long userId)
    {
       // HttpServletRequest request = new HttpServletRequest("https://api.github.com/users/{userId}/repos");

    }
/*
    @RequestMapping(value = "/allProjectBySession/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getProjectBySession(@PathVariable Long sessionId)
    {
        return service.getProjectBySession(sessionId);
    }
*/
    @RequestMapping(value = "/project/{ProjectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Project> getProject(@PathVariable Long ProjectId)
    {
         return service.getProjectById(ProjectId);
    }

    @RequestMapping(value = "/deleteProject/{ProjectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProject(@PathVariable Long ProjectId)
    {
        service.deleteProject(ProjectId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getProject/{username}")
    public @ResponseBody
    List<Project> getProjects(@PathVariable String username) {

        RestTemplate restTemplate = new RestTemplate();
        String Url = "https://api.github.com/users/"+username+"/repos";
        List<LinkedHashMap<String,String>> repos = restTemplate.getForObject(Url, List.class);
        List<Project> projects = new ArrayList<Project>();

        for(LinkedHashMap<String,String>  repo : repos)
        {
            System.out.println(repo.get("name"));
            System.out.println(repo.get("html_url"));
            System.out.println(repo.get("description"));
            System.out.println(repo.get("created_at"));
            Project p = new Project(repo.get("name"),repo.get("description"),repo.get("html_url"), repo.get("created_at") );
            service.createProject(p);
            projects.add(p);
        }

        return projects;

    }
}
