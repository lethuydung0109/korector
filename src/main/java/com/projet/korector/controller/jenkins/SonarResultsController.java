package com.projet.korector.controller.jenkins;

import com.projet.korector.controller.SessionController;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Session;
import com.projet.korector.entity.SonarResults;
import com.projet.korector.payload.request.UserRequest;
import com.projet.korector.payload.response.MessageResponse;
import com.projet.korector.repository.ProjectRepository;
import com.projet.korector.repository.SessionRepository;
import com.projet.korector.repository.SonarResultsRepository;
import com.projet.korector.services.ProjectService;
import com.projet.korector.services.SessionService;
import com.projet.korector.services.SonarResultsService;
import com.projet.korector.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/sonarResults")

public class SonarResultsController {


    @Autowired
    private SonarResultsService sonarResultsService;



    @Autowired

    private ProjectService projectService;
    @Autowired
    private SessionService sessionService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SessionRepository sessionRepository;

    final static Logger log = LoggerFactory.getLogger(SessionController.class);

    @RequestMapping(value = "/saveResults", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> saveResults(@RequestBody SonarResults sonarResults){
        //System.out.println("Project id dans controller" + projectId);
     /*   SonarResults sonarR =  new SonarResults(sonarResults.getBugs(),
                sonarResults.getVuls(),
                sonarResults.getDebt(),sonarResults.getSmells(),sonarResults.getCoverage(),
                sonarResults.getDups(),sonarResults.getDupsBlock(),sonarResults.getProject_id(),sonarResults.getSession_id(),sonarResults.getNote_finale()
                ,sonarResults.getDate()); */
        sonarResultsService.saveSonarResults(sonarResults);
        return ResponseEntity.ok(new MessageResponse("Results sonar save!"));

    }

    @GetMapping("/all")
    @RequestMapping(value = "/getResultsBySessProj/{sessionId}/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SonarResults getLastResultsSonar(@PathVariable("sessionId") Long sessionId, @PathVariable("projectId") Long projectId){
        return sonarResultsService.getLastBuildResults(sessionId,projectId);

    }

    @GetMapping("/all")
    @RequestMapping(value = "/runExistsSession/{sessionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean runExistsBySession(@PathVariable("sessionId") Long sessionId){
        return  sonarResultsService.runExistsBySession(sessionId);

    }

    @GetMapping("/all")
    @RequestMapping(value = "/runExistsSessionProject/{sessionId}/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean runExistsBySession(@PathVariable("sessionId") Long sessionId, @PathVariable("projectId")  Long projectId){

        return  sonarResultsService.runExistsBySessionProject(sessionId,projectId);

    }
    /*
    public ResponseEntity<?> getLastNote(){

    }*/
/*
    @RequestMapping(value = "/updateResults/{projectId}/{sessionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)


    public ResponseEntity<SonarResults> updateResults(@RequestBody SonarResults sonarResults, @PathVariable Long projectId,  @PathVariable Long sessionId){
        log.info("Donnes before" + sonarResults.toString() );
        SonarResults newSonarR = new SonarResults(sonarResults.getId(),sonarResults.getBugs(),
                sonarResults.getVuls(),
                sonarResults.getDebt(),sonarResults.getSmells(),sonarResults.getCoverage(),
                sonarResults.getDups(),sonarResults.getDupsBlock());
        Project project = projectRepository.findById(projectId).get();

         // newSonarR.getResultsSonarProjects().add(this.projectRepository.findById(projectId).get()));
        System.out.println("Project id dans ctrl " + projectId);
        System.out.println("Project id dans ctrl 2" + projectRepository.findById(projectId).get());
        Session session = sessionService.getSessionById(sessionId);
       // newSonarR.setResultsSonarProjects(project);
      //  newSonarR.setResultsSonarSessions(session);
        System.out.println("Donnes After" + newSonarR.toString() );

        if (!sonarResultsRepository.existsById(newSonarR.getId())) {
            return new ResponseEntity<SonarResults>(HttpStatus.NOT_FOUND);
        }

        SonarResults book =sonarResultsRepository.save(newSonarR);
        if (book != null) {
            // BookDTO bookDTO = mapBookToBookDTO(book);
            return new ResponseEntity<SonarResults>(book, HttpStatus.OK);
        }
        return new ResponseEntity<SonarResults>(HttpStatus.NOT_MODIFIED);




        //   return new ResponseEntity<SonarResults>(this.sonarResultsRepository.saveAndFlush(newSonarR), HttpStatus.OK);

    } */
}
