package com.projet.korector.services;

import com.projet.korector.controller.SessionController;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.repository.ProjectRepository;
import com.projet.korector.repository.RunRepository;
import com.projet.korector.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SessionService {

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private RunRepository runRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public Session createSession(Session session)
    {
        //System.out.println("Session json : "+session.getName());
        return this.sessionRepository.save(session);
    }

    public List<Session> getAllSessions()
    {
        System.out.println(" Toute la bdd : "+sessionRepository.findAll());
        return sessionRepository.findAll();
    }

    public List<Session> getSessionByUser(Long userId)
    {
        //sessionRepository.findAll().stream().filter(session -> { session.getUser().getId().equals(userId)});
        return null;
    }

    public Set<Project> getSessionProjects(Long sessionId)
    {
       return this.sessionRepository.findById(sessionId).orElse(null).getProjects();
    }

    public void addProjectToSession()
    {
        // l'idSession d'un projet passe de null à l'id de la session
    }

    public void deleteProjectFromSession(Long id)
    {
        //projectRepository.delete(id);
    }

    public void deleteSession(Long id){
        if(this.sessionRepository.findById(id).isPresent()) this.sessionRepository.deleteById(id);
    }

    public void deleteAllSessions()
    {
        this.sessionRepository.deleteAll();
    }
}
