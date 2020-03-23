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

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/project")
public class ProjectController {

    final static Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService service;

    @RequestMapping(value = "/createProject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project createProject(ProjectImp project)
    {
        return service.createProject(project);
    }

    @RequestMapping(value = "/allProjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getAllProjects()
    {
        return service.getAllProjects();
    }

    @RequestMapping(value = "/allProjectByUser/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getProjectByUser(@PathVariable Long userId)
    {
        return service.getProjectByUser(userId);
    }

    @RequestMapping(value = "/allProjectBySession/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getProjectBySession(@PathVariable Long sessionId)
    {
        return service.getProjectBySession(sessionId);
    }

    @RequestMapping(value = "/deleteProject/{ProjectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProject(@PathVariable Long ProjectId)
    {
        service.deleteProject(ProjectId);
    }
}
