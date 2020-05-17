package com.projet.korector.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "runProject")
public class RunSonar {

// Attribute for criteria

private Session s ;
private Long project_id;
private boolean errorSonnar;

    public boolean isErrorSonnar() {
        return errorSonnar;
    }

    public void setErrorSonnar(boolean errorSonnar) {
        this.errorSonnar = errorSonnar;
    }

    public Session getS() {
        return s;
    }

    public void setS(Session s) {
        this.s = s;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public RunSonar(Long project_id){
    this.project_id = project_id;
}
    private Double bugsMax;

    private Double bugsPoids;


    private Double vulsMax;

    private Double vulsPoids;


    private Double debtMax;

    private Double debtPoids;


    private Double smellsMax;

    private Double smellsPoids;

    private Double coverageMax;

    private Double coveragePoids;
    private Double dupsMax;
    private Double dupsPoids;


    private Double dupBlockMax;

    private Double dupBlockPoids;


    private Double finalNote;

    public Double getBugsMax() {
        return bugsMax;
    }

    public void setBugsMax(Double bugsMax) {
        this.bugsMax = bugsMax;
    }

    public Double getBugsPoids() {
        return bugsPoids;
    }

    public void setBugsPoids(Double bugsPoids) {
        this.bugsPoids = bugsPoids;
    }

    public Double getCoverageMax() {
        return coverageMax;
    }

    public void setCoverageMax(Double coverageMax) {
        this.coverageMax = coverageMax;
    }

    public Double getCoveragePoids() {
        return coveragePoids;
    }

    public void setCoveragePoids(Double coveragePoids) {
        this.coveragePoids = coveragePoids;
    }

    public Double getSmellsMax() {
        return smellsMax;
    }

    public void setSmellsMax(Double csmellsMax) {
        smellsMax = csmellsMax;
    }

    public Double getCsmellsPoids() {
        return smellsPoids;
    }

    public void setCsmellsPoids(Double csmellsPoids) {
        smellsPoids = csmellsPoids;
    }

    public Double getDebtMax() {
        return debtMax;
    }

    public void setDebtMax(Double debtMax) {
        this.debtMax = debtMax;
    }

    public Double getDebtPoids() {
        return debtPoids;
    }

    public void setDebtPoids(Double debtPoids) {
        this.debtPoids = debtPoids;
    }

    public Double getDupBlockMax() {
        return dupBlockMax;
    }

    public void setDupBlockMax(Double dupBlockMax) {
        this.dupBlockMax = dupBlockMax;
    }

    public Double getDupBlockPoids() {
        return dupBlockPoids;
    }

    public void setDupBlockPoids(Double dupBlockPoids) {
        this.dupBlockPoids = dupBlockPoids;
    }

    public Double getDupsMax() {
        return dupsMax;
    }

    public void setDupsMax(Double dupsMax) {
        this.dupsMax = dupsMax;
    }

    public Double getDupsPoids() {
        return dupsPoids;
    }

    public void setDupsPoids(Double dupsPoids) {
        this.dupsPoids = dupsPoids;
    }

    public Double getFinalNote() {
        return finalNote;
    }

    public void setFinalNote(Double finalNote) {
        this.finalNote = finalNote;
    }

    public Double getVulsMax() {
        return vulsMax;
    }

    public void setVulsMax(Double vulsMax) {
        this.vulsMax = vulsMax;
    }

    public Double getVulsPoids() {
        return vulsPoids;
    }

    public void setVulsPoids(Double vulsPoids) {
        this.vulsPoids = vulsPoids;
    }
}
