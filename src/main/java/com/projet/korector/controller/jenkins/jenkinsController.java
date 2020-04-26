package com.projet.korector.controller.jenkins;

import com.projet.korector.jenkins.Jenkins;
import com.projet.korector.jenkins.JenkinsService;
import com.projet.korector.sonarqube.SonarQube;
import com.projet.korector.sonarqube.SonarQubeImpl;
import com.projet.korector.util.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static com.projet.korector.jenkins.constants.*;

@RestController

public class jenkinsController {



    @Autowired
    private JenkinsService jenkinsService;

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


    private String createJob(String name, String url){
        url = url.replace(',', '/');
        XmlReader xmlReader = new XmlReader(url, "url");
        String xmlJob = xmlReader.formatXML();
        System.out.println(xmlJob);
        String result = jenkinsService.createJob(name, xmlJob);
        return result;
    }


    private Map<String,String> build(String name, String url){
        jenkinsService = new Jenkins(USERNAME_JENKINS,PASSWORD_JENKINS,URL_JENKINS);
        boolean isCreation = false;

        if(!jenkinsService.isJobExist(name)) {
            String creation = createJob(name,url);
            isCreation = true;
            if (creation.equals(name))
                System.out.println("La Création du job " + name + " est finie");
            else{
                return Collections.singletonMap("ERROR",creation);
            }
        }

        System.out.println("Lancement du build");
        System.out.println("Build Fini" + jenkinsService.buildJob(name,isCreation));

        if(!jenkinsService.getResultLasBuild(name).equals("SUCCESS")) {
            return Collections.singletonMap("ERROR", jenkinsService.getOutPut(name));
        } else {
            SonarQube sonarQube = new SonarQubeImpl();
            return sonarQube.getMetrics(name);
        }
    }


}