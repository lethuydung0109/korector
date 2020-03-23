package com.projet.korector.controller;

import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.model.SessionImp;
import com.projet.korector.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/session")
public class SessionController {

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService service;

    @RequestMapping(value = "/createSession", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Session createSession(SessionImp session)
    {
        return service.createSession(session);
    }

    @RequestMapping(value = "/allSessions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Session> getAllSessions()
    {
        return service.getAllSessions();
    }

    @RequestMapping(value = "/allSessionByUser/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Session> getSessionByUser(@PathVariable Long userId)
    {
       return service.getSessionByUser(userId);
    }

    @RequestMapping(value = "/sessionProjects/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getSessionProjects(@PathVariable Long sessionId)
    {
        return service.getSessionProjects(sessionId);
    }

    @RequestMapping(value = "/addProjectToSession/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addProjectToSession(@PathVariable Long projectId)
    {
        service.addProjectToSession();
    }

    @RequestMapping(value = "/deleteProjectFromSession/{projectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProjectFromSession(@PathVariable Long projectId)
    {
        service.deleteProjectFromSession(projectId);
    }

    @RequestMapping(value = "/deleteSession/{sessionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSession(@PathVariable Long sessionId)
    {
        service.deleteSession(sessionId);
    }
}
