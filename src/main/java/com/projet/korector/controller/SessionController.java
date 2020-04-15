package com.projet.korector.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.model.SessionImp;
import com.projet.korector.model.User;
import com.projet.korector.security.services.UserDetailsImpl;
import com.projet.korector.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class SessionController {

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService service;

    @Autowired
    private UserController userController;

    @GetMapping("/all")
    @RequestMapping(value = "/createSession", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Session createSession(@RequestBody Session session)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = this.userController.findById(userDetails.getId());
        return service.createSession(session,currentUser);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/allSessions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Session> getAllSessions()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json="";
        try {
            json = mapper.writeValueAsString(service.getAllSessions());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return service.getAllSessions();
    }

    @GetMapping("/all")
    @RequestMapping(value = "/sessionById/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Session getSessionById(@PathVariable("sessionId") Long sessionId)
    {
        return service.getSessionById(sessionId);
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
    @RequestMapping(value = "/addProjectToSession/{sessionId}/{projectId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addProjectToSession(@PathVariable("sessionId") Long sessionId, @PathVariable("projectId") Long projectId)
    {
        service.addProjectToSession(sessionId,projectId);
    }

    @GetMapping("/all")
    @RequestMapping(value = "/deleteProjectFromSession/{sessionId}/{projectId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProjectFromSession(@PathVariable("sessionId") Long sessionId, @PathVariable("projectId") Long projectId)
    {
        service.deleteProjectFromSession(sessionId,projectId);
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
