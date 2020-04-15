package com.projet.korector.services;

import com.projet.korector.controller.SessionController;
import com.projet.korector.controller.UserController;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.model.User;
import com.projet.korector.repository.ProjectRepository;

import com.projet.korector.repository.RunRepository;
import com.projet.korector.repository.SessionRepository;
import com.projet.korector.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
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

    @Autowired
    private UserController userController;

//    @PersistenceContext
//    private EntityManager em;


    public Session createSession(Session session,User currentUser)
    {
        log.info("objet angular reçu pour création :"+session.toString());
        Set<Project> projects = session.getProjects();
        log.info("liste des projets de la session : "+projects);
        session.setProjects(new HashSet<>());
        Session createdSession = this.sessionRepository.save(session);
        log.info("createdSession - Etape 1 : "+createdSession);
        createdSession.getProjects().addAll(projects);

        currentUser.getSessions().add(createdSession);
        createdSession.getUsers().add(currentUser);

        return this.sessionRepository.saveAndFlush(createdSession);
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
        Set<Project> projects = new HashSet<Project>();
        projects.addAll(this.sessionRepository.findById(sessionId).get().getProjects());
        return projects;
    }

    public void addProjectToSession(Long sessionId,Long projectId)
    {
        Project putProject=this.projectRepository.findById(projectId).get();
        Session putSession=this.sessionRepository.findById(sessionId).get();

        putSession.getProjects().add(putProject);
        putProject.getSessions().add(putSession);
        this.projectRepository.saveAndFlush(putProject);
        this.sessionRepository.saveAndFlush(putSession);

    }

    public void deleteProjectFromSession(Long sessionId,Long projectId)
    {
        Project deleteProject=this.projectRepository.findById(projectId).get();
        Session putSession=this.sessionRepository.findById(sessionId).get();

        putSession.getProjects().remove(deleteProject);
        deleteProject.getSessions().remove(putSession);
        this.projectRepository.saveAndFlush(deleteProject);
        this.sessionRepository.saveAndFlush(putSession);
    }

    public void deleteSession(Long id){
        if(this.sessionRepository.findById(id).isPresent()) this.sessionRepository.deleteById(id);
    }

    public void deleteAllSessions()
    {
        this.sessionRepository.deleteAll();
    }

    public Session getSessionById(Long sessionId) {
        return this.sessionRepository.findById(sessionId).orElse(null);
    }
}
