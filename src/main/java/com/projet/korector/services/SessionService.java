package com.projet.korector.services;

import com.projet.korector.controller.SessionController;
import com.projet.korector.controller.UserController;
import com.projet.korector.entity.Criteria;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Run;
import com.projet.korector.entity.Session;
import com.projet.korector.entity.User;
import com.projet.korector.repository.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private CriteriaRepository criteriaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    public Session createSession(Session session,User currentUser)
    {
        log.info("objet angular reçu pour création :"+session.toString());
        Set<Project> projects = session.getProjects();
        Set<Criteria> criterias = session.getCriterias();

        session.setProjects(new HashSet<>());
        session.setCriterias(new HashSet<>());
        Session createdSession = this.sessionRepository.save(session);

        createdSession.getProjects().addAll(projects);
        createdSession.getCriterias().addAll(criterias);

        log.info("createdSession : "+createdSession);
        currentUser.getSessions().add(createdSession);
        createdSession.getUsers().add(currentUser);

        return this.sessionRepository.saveAndFlush(createdSession);
    }

    public Session updateSession(Session session)
    {
        Session newSession=null;
        if(this.sessionRepository.findById(session.getId()).isPresent())
        {
            this.setSessionProjects(session.getId(),session.getProjects());
            this.setSessionCriterias(session.getId(),session.getCriterias());
            newSession=this.sessionRepository.save(session);
        }
        return newSession;
    }

    public List<Session> getAllSessions()
    {
        return sessionRepository.findAll();
    }

    public Set<Session> getSessionWithDateDepotNotNull()
    {
        Set<Session> sessions = this.sessionRepository.findAll().stream().filter(session -> session.getDate_depot() != null).collect(Collectors.toSet());
        log.info("Session- date depot not null : "+sessions);
        return sessions;
    }

    public Set<Project> getSessionProjects(Long sessionId)
    {
        return new HashSet<Project>(this.sessionRepository.findById(sessionId).get().getProjects());
    }

    public Set<Criteria> getSessionCriterias(Long sessionId)
    {
        return new HashSet<Criteria>(this.sessionRepository.findById(sessionId).get().getCriterias());
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

    public void setSessionProjects(Long sessionId,Set<Project> projects)
    {
        Set<Project> putProjects= new HashSet<>();
        projects.forEach(project->{
            putProjects.add(this.projectRepository.findById(project.getId()).get());
        });

        Session putSession=this.sessionRepository.findById(sessionId).get();
        putSession.setProjects(putProjects);

        putProjects.forEach(project -> {
            project.getSessions().add(putSession);
            this.projectRepository.saveAndFlush(project);
        });

        this.sessionRepository.saveAndFlush(putSession);
    }

    public void setSessionCriterias(Long sessionId,Set<Criteria> criterias)
    {
        Set<Criteria> putCriterias= new HashSet<>();
        criterias.forEach(criteria->{
            putCriterias.add(this.criteriaRepository.findById(criteria.getId()).get());
        });

        Session putSession=this.sessionRepository.findById(sessionId).get();
        putSession.setCriterias(putCriterias);

        putCriterias.forEach(criteria -> {
            criteria.getSessions().add(putSession);
            this.criteriaRepository.saveAndFlush(criteria);
        });

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

    public void deleteSession(Long id, User user){
        Session deletedSession=this.sessionRepository.findById(id).get();
        boolean isPresent = this.sessionRepository.findById(id).isPresent();
        if(isPresent)
        {
            Set<Project> projects= deletedSession.getProjects();
            Set<Criteria> criterias = deletedSession.getCriterias();
            Set<Run> runs = deletedSession.getRuns();

            projects.forEach(project -> {
                this.projectRepository.findById(project.getId()).get().getSessions().remove(deletedSession);
                this.projectRepository.saveAndFlush(project);
            });

            criterias.forEach(criteria -> {
                this.criteriaRepository.findById(criteria.getId()).get().getSessions().remove(deletedSession);
                this.criteriaRepository.saveAndFlush(criteria);
            });

            projects.clear();
            criterias.clear();
            runs.clear();

            user.getSessions().remove(deletedSession);
            this.userRepository.save(user);
            deletedSession.getUsers().remove(user);

            this.sessionRepository.saveAndFlush(deletedSession);
            this.sessionRepository.deleteById(id);
        }
    }

    public void deleteAllSessions(User user)
    {
        this.sessionRepository.findAll().forEach(session -> {
            this.deleteSession(session.getId(), user);
        });
    }

    public Session getSessionById(Long sessionId) {
        return this.sessionRepository.findById(sessionId).orElse(null);
    }

    public Set<Run> getSessionRuns(Long sessionId)
    {
        Set<Run> runs = new HashSet<Run>();
        runs.addAll(this.sessionRepository.findById(sessionId).get().getRuns());
        return runs;
    }

    public void exportCSV(Long runId, HttpServletResponse response) {

        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();

        //set file name and content type
        String filename = "run_"+date+".csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        Run run = this.runRepository.findById(runId).get();

        List<Project> sessionsProject = new ArrayList<Project>(this.getSessionProjects(run.getSession().getId()));

        try (
                CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT
                        .withHeader("ID", "FirstName", "LastName"));
        ) {
            for (Project project : sessionsProject) {
                List<? extends Serializable> data = Arrays.asList(
                        project.getId(),
                        project.getName(),
                        project.getNote(),
                        project.getUrl()
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }
}
