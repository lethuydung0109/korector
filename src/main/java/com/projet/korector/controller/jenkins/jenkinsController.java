package com.projet.korector.controller.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.projet.korector.entity.*;
import com.projet.korector.jenkins.Jenkins;
import com.projet.korector.jenkins.JenkinsService;
import com.projet.korector.payload.response.MessageResponse;
import com.projet.korector.services.CriteriaService;
import com.projet.korector.services.SessionService;
import com.projet.korector.sonarqube.SonarQube;
import com.projet.korector.sonarqube.SonarQubeImpl;
import com.projet.korector.util.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URI;
import java.util.*;

import static com.projet.korector.jenkins.constants.*;

@RestController
@RequestMapping(value = "/api/jenkins")

public class jenkinsController {



    @Autowired
    private JenkinsService jenkinsService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CriteriaService criteriaService;


   // @PostMapping("/result/{name}/{url}")
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    /*public Analyse saveAnalyse(@PathVariable("name") String name, @PathVariable("url") String url,@RequestBody Analyse analyse){
        Analyse newAnalyse = this.doAnalyse(name,url,analyse);
        return this.analyseService.save(newAnalyse);
    }

    private Analyse doAnalyse(String name, String url, Analyse analyse){
        System.out.println(analyse);
        Map<String,String> resutlSonar = this.build(name,url);

        if(resutlSonar.containsKey("ERROR")){
            Analyse newAnalyse = new Analyse(name);
            newAnalyse.setError(true);
            return newAnalyse;
        }

        Analyse newAnalyse = new Analyse(analyse.getBugMax(),analyse.getBugPoids(),analyse.getVulMax(),analyse.getVulPoids(),analyse.getDebtMax(),analyse.getDebtPoids(),analyse.getSmellsMax(),analyse.getSmellsPoids(),analyse.getCoveragePoids(),analyse.getDuplicationPoids(),analyse.getDupBlockMax(),analyse.getDupBlockPoids());
        newAnalyse.setError(false);
        double noteBug = Double.parseDouble(resutlSonar.get("nombre de bugs")) >= analyse.getBugMax() ? 0 : 1 - Double.parseDouble(resutlSonar.get("nombre de bugs"))/analyse.getBugMax();
        newAnalyse.setNoteBug(noteBug);
        double noteVul = Double.parseDouble(resutlSonar.get("vulnérabilités")) >= analyse.getVulMax() ? 0 : 1 - Double.parseDouble(resutlSonar.get("vulnérabilités"))/analyse.getVulMax();
        newAnalyse.setNoteVul(noteVul);
        double noteDebt = Double.parseDouble(resutlSonar.get("debt")) >= analyse.getDebtMax() ? 0 : 1 - Double.parseDouble(resutlSonar.get("debt"))/analyse.getDebtMax();
        newAnalyse.setNoteDebt(noteDebt);
        double noteSmell = Double.parseDouble(resutlSonar.get("code smells")) >= analyse.getSmellsMax() ? 0 : 1 - Double.parseDouble(resutlSonar.get("code smells"))/analyse.getSmellsMax();
        newAnalyse.setNoteSmells(noteSmell);
        double noteCoverage = Double.parseDouble(resutlSonar.get("coverage"))/100;
        newAnalyse.setNoteCoverage(noteCoverage);
        double noteDuplications = (100-Double.parseDouble(resutlSonar.get("duplications")))/100;
        newAnalyse.setNoteDuplication(noteDuplications);
        double noteBlock = Double.parseDouble(resutlSonar.get("blocs dupliqués")) >= analyse.getDupBlockMax() ? 0 : 1 - Double.parseDouble(resutlSonar.get("blocs dupliqués"))/analyse.getDupBlockMax();
        newAnalyse.setNoteDupBlock(noteBlock);

        double note = (noteBug) * analyse.getBugPoids() + (noteVul) * analyse.getVulPoids() + (noteDebt) * analyse.getDebtPoids() + (noteSmell) * analyse.getSmellsPoids() + (noteCoverage) * analyse.getCoveragePoids()
                + (noteDuplications) * analyse.getDuplicationPoids() + (noteBlock) * analyse.getDupBlockPoids();

        newAnalyse.setNoteFinale((note*20)/(analyse.getBugPoids()+analyse.getVulPoids()+analyse.getDebtPoids()+analyse.getSmellsPoids()+analyse.getCoveragePoids()+analyse.getDuplicationPoids()+analyse.getDupBlockPoids()));
        newAnalyse.setNomProjet(name);
        newAnalyse.setDate(Calendar.getInstance());

        return newAnalyse;
    } */


   /* @PostMapping("/all")
    @RequestMapping(value = "/test/{sessionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public void testCriteria(@PathVariable Long sessionId){
        System.out.println("Criteria Test Start");

        Set<Criteria> sessionsCriteria = sessionService.getSessionCriterias(sessionId);
        System.out.println("After`");

        List <String> statsCriteria= new ArrayList<String> ();
        List <Criteria> dynamicCriteria = new ArrayList<Criteria>();
        for(Criteria criteriaId : sessionsCriteria){
            Long idCriteria = criteriaId.getId();
            Optional<Criteria> criteriaData = criteriaService.findById(idCriteria);
            Criteria criteriaObject = criteriaData.get();
            if(criteriaObject.getType()=="Statique"){
                statsCriteria.add(criteriaObject.getName());
                System.out.println("Criteria Stats " + criteriaObject.getName());
            }
            else {
                System.out.println("Criteria Dynamique" + criteriaObject.getName());

                dynamicCriteria.add(criteriaObject);

            }

        }
    }
*/
    @RequestMapping(value = "/run/{nomBuild}/{url}/{sessionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private void run(@PathVariable String nomBuild, @PathVariable String url,@PathVariable Long sessionId){
        System.out.println("Lancement de l'analyse");
        Map<String,String> sonarBuild= this.build(nomBuild,url,true);

      /*  Set<Criteria> sessionsCriteria = sessionService.getSessionCriterias(sessionId);
        List <String> statsCriteria= new ArrayList<String> ();
        List <Criteria> dynamicCriteria = new ArrayList<Criteria>(); */

        double noteBug=0,noteVul=0,noteDebt=0,noteSmell=0,noteCoverage=0,noteDuplications=0,noteBlock=0,finaleNote;

        if(sonarBuild.containsKey("ERROR")){
            RunSonar newRun = new RunSonar(sessionId);
            newRun.setErrorSonnar(true);
            //return newRun;
            System.out.println("Error");
        }
        RunSonar runSonar= new RunSonar(sessionId);
       /* for(Criteria criteriaId : sessionsCriteria){
          Long idCriteria = criteriaId.getId();
            Optional<Criteria> criteriaData = criteriaService.findById(idCriteria);
           Criteria criteriaObject = criteriaData.get();
            if(criteriaObject.getType()=="Statique"){
                statsCriteria.add(criteriaObject.getName());
                System.out.println("Criteria Stats " + criteriaObject.getName());
            }
            else {
                System.out.println("Criteria Dynamique" + criteriaObject.getName());

                dynamicCriteria.add(criteriaObject);

            }

        }

for (String name : statsCriteria) {
    if(name == "nombre de bugs"){ */
      noteBug = Double.parseDouble(sonarBuild.get("nombre de bugs")) >= 500 ? 0 : 1 - Double.parseDouble(sonarBuild.get("nombre de bugs")) / 500;
        //RunSonar.setNote(noteBug);
   /* }
    if(name == "vulnérabilités") { */

       noteVul = Double.parseDouble(sonarBuild.get("vulnérabilités")) >= 500 ? 0 : 1 - Double.parseDouble(sonarBuild.get("vulnérabilités")) / 500;
        //   newAnalyse.setNoteVul(noteVul);
   /* }
    if(name == "debt") { */
       noteDebt = Double.parseDouble(sonarBuild.get("debt")) >= 2500 ? 0 : 1 - Double.parseDouble(sonarBuild.get("debt")) / 2500;
        // newAnalyse.setNoteDebt(noteDebt);
   /* }
    if(name == "code smells") { */

        noteSmell = Double.parseDouble(sonarBuild.get("code smells")) >= 500 ? 0 : 1 - Double.parseDouble(sonarBuild.get("code smells")) / 500;
        //    newAnalyse.setNoteSmells(noteSmell);
    /*}
    if(name == "coverage") { */

      noteCoverage = Double.parseDouble(sonarBuild.get("coverage")) / 100;
        // newAnalyse.setNoteCoverage(noteCoverage);
   /* }
    if(name == "duplications") { */
       noteDuplications = (100 - Double.parseDouble(sonarBuild.get("duplications"))) / 100;
        //  newAnalyse.setNoteDuplication(noteDuplications);
  /*  }
    if(name == "blocs dupliqués") { */
       noteBlock = Double.parseDouble(sonarBuild.get("blocs dupliqués")) >= 100 ? 0 : 1 - Double.parseDouble(sonarBuild.get("blocs dupliqués")) / 100;
        //      newAnalyse.setNoteDupBlock(noteBlock);
        //   double noteFinale = (resultBug) + res
    /*}
} */
     double finaleNoteStats = (noteBug) * 10 + (noteVul) * 10+ (noteDebt) * 10 + (noteSmell) * 10 + (noteCoverage) * 10
                + (noteDuplications) * 10 + (noteBlock) * 10;

    finaleNote = finaleNoteStats;
        runSonar.setFinalNote((finaleNoteStats*20)/100);

        runSonar.setFinalNote((finaleNote));
    System.out.println("fINALE NOTE" + finaleNote);

    }

    private String createJob(String name, String url){
        System.out.println("Test Passed ");
        System.out.println("Ancien  " + url);

        url = url.replace(',', '/');
        System.out.println("New Url " + url);

        String properties = "sonar.projectKey="+ name + "\n" +
                " sonar.projectName="+ name + "\n" +
                " sonar.java.binaries =src/main/java/";

        XmlReader xmlReader = new XmlReader(url,properties);
        String xmlJob = xmlReader.formatXML();System.out.println("New Xml Job " + xmlJob);
   ;
    String result = jenkinsService.createJob(name, xmlJob,true);
        System.out.println("Result  " + result);

        return result;

    }

    @RequestMapping(value = "/build/{name}/{url}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private Map<String,String>build(@PathVariable String name, @PathVariable String url, boolean boo){
        jenkinsService = new Jenkins(USERNAME_JENKINS,PASSWORD_JENKINS,URL_JENKINS);
        boolean isCreation = false;
String str;
        if(!jenkinsService.isJobExist(name)) {
            String creation = createJob(name,url);
            System.out.println("Job name : " + creation);
            isCreation = true;
            if (creation.equals(name)) {

                System.out.println("La Création du job " + name + " est finie");
                //return str;
            }
            else{
                return Collections.singletonMap("ERROR",creation);
            }
        }

        System.out.println("Lancement du build");
        System.out.println("Build Fini" + jenkinsService.buildJob(name,isCreation));

        if(!jenkinsService.getResultLasBuild(name).equals("SUCCESS")) {
            str ="error build";


            return Collections.singletonMap("ERROR", jenkinsService.getOutPut(name));
        } else {
            SonarQube sonarQube = new SonarQubeImpl();
            System.out.println("Sonarqube metrics" + sonarQube.getMetrics(name));

           return sonarQube.getMetrics(name);
        }
    }


}