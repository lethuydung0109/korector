package com.projet.korector.services;

import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.model.SessionImp;
import com.projet.korector.repository.ProjectRepository;
import com.projet.korector.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public Session createSession(SessionImp sessionImp)
    {

        //sessionRepository.save(session);
        return null;
    }

    public List<Session> getAllSessions()
    {
        return sessionRepository.findAll();
    }

    public List<Session> getSessionByUser(Long userId)
    {
        //sessionRepository.findAll().stream().filter(session -> { session.getUser().getId().equals(userId)});
        return null;
    }

    public List<Project> getSessionProjects(Long sessionId)
    {
        return null;
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

    }
}
