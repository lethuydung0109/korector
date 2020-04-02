package com.projet.korector.controller;

import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.model.SessionImp;
import com.projet.korector.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class SessionController {

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService service;

    @GetMapping("/all")
    @RequestMapping(value = "/createSession", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Session createSession(@RequestBody Session session)
    {
        return service.createSession(session);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/allSessions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Session> getAllSessions()
    {
        return service.getAllSessions();
    }

    @GetMapping("/all")
    @RequestMapping(value = "/allSessionByUser/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Session> getSessionByUser(@PathVariable("userId") Long userId)
    {
       return service.getSessionByUser(userId);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/sessionProjects/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Project> getSessionProjects(@PathVariable("sessionId") Long sessionId)
    {
        return service.getSessionProjects(sessionId);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/addProjectToSession/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addProjectToSession(@PathVariable("projectId") Long projectId)
    {
        service.addProjectToSession();
    }

    @GetMapping("/all")
    @RequestMapping(value = "/deleteProjectFromSession/{projectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProjectFromSession(@PathVariable("projectId") Long projectId)
    {
        service.deleteProjectFromSession(projectId);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/deleteSession/{sessionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSession(@PathVariable("sessionId") Long sessionId)
    {
        service.deleteSession(sessionId);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/deleteAllSessions", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAllSessions()
    {
        service.deleteAllSessions();
    }
}
