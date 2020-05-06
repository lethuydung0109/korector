package com.projet.korector.controller.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.projet.korector.jenkins.Jenkins;
import com.projet.korector.jenkins.JenkinsService;
import com.projet.korector.payload.response.MessageResponse;
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
import java.util.Collections;
import java.util.Map;

import static com.projet.korector.jenkins.constants.*;

@RestController
@RequestMapping(value = "/api/jenkins")

public class jenkinsController {



    @Autowired
    private JenkinsService jenkinsService;

    //@PostMapping("/result/{name}/{url}")
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    /*
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


   // @RequestMapping(value = "/run/{name}/{url}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    /*private void run(@PathVariable String nomBuild, @PathVariable String url){
        System.out.println("Lancement de l'analyse");

        Map<String,String> sonarBuild= this.build(nomBuild,url,true);
        double resultBug = 1 - Double.parseDouble(sonarBuild.get("nombre de bugs")) /100;
        double resultVul = 1 -  Double.parseDouble(sonarBuild.get("vulnérabilités"))/100;
        double resultDebt =1 - Double.parseDouble(sonarBuild.get("debt"))/100;
        double resultSmell = Double.parseDouble(sonarBuild.get("code smells"))/100 ;
        double resultCoverage = Double.parseDouble(sonarBuild.get("coverage"))/100;
     //   double noteFinale = (resultBug) + res

    } */
    private String createJob(String name, String url){
        System.out.println("Test Passed ");
        System.out.println("Ancien  " + url);

        url = url.replace(',', '/');
        System.out.println("New Url " + url);

        XmlReader xmlReader = new XmlReader(url, "url");
        String xmlJob = xmlReader.formatXML();System.out.println("New Xml Job " + xmlJob);
   ;
    String result = jenkinsService.createJob(name, xmlJob,true);
        System.out.println("Result  " + result);

        return result;

    }

    @RequestMapping(value = "/build/{name}/{url}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    private ResponseEntity<?> build(@PathVariable String name, @PathVariable String url, boolean boo){
        jenkinsService = new Jenkins(USERNAME_JENKINS,PASSWORD_JENKINS,URL_JENKINS);
        boolean isCreation = false;

        if(!jenkinsService.isJobExist(name)) {
            String creation = createJob(name,url);
            System.out.println("Job name : " + creation);
            isCreation = true;
            if (creation.equals(name))
                System.out.println("La Création du job " + name + " est finie");
            else{
                //return Collections.singletonMap("ERROR",creation);
                return ResponseEntity.ok(new MessageResponse("Error"));

            }
        }

        System.out.println("Lancement du build");
        System.out.println("Build Fini" + jenkinsService.buildJob(name,isCreation));

        if(!jenkinsService.getResultLasBuild(name).equals("SUCCESS")) {
            return ResponseEntity.ok(new MessageResponse("Succes"));

            // return Collections.singletonMap("ERROR", jenkinsService.getOutPut(name));
        } else {
            SonarQube sonarQube = new SonarQubeImpl();
            System.out.println("Sonarqube metrics" + sonarQube.getMetrics(name));
            return ResponseEntity.ok(new MessageResponse("Metrics"));

          //  return sonarQube.getMetrics(name);
        }
    }


}