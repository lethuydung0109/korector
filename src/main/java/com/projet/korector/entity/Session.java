package com.projet.korector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="session")
public class Session implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String date_depot;
    private String heureDepot;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="sessions_projects",
            joinColumns = {
                    @JoinColumn(name = "session_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})

    private Set<Project> projects = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="sessions_criterias",
            joinColumns = {
                    @JoinColumn(name = "session_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "criteria_id",
                            nullable = false, updatable = false)})
    private Set<Criteria> criterias = new HashSet<>();

    @ManyToMany(mappedBy = "sessions",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Run> runs = new HashSet<>();

    public Session() { }

    public Session(String n, String date, String heure) {
        this.name = n;
        this.date_depot=date;
        this.heureDepot=heure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects= projects;
    }

    public Set<Run> getRuns() {
        return runs;
    }

    public void setRuns(Set<Run> runs) {
        this.runs = runs;
    }

    public String getDate_depot() {
        return date_depot;
    }

    public void setDate_depot(String date_depot) {
        this.date_depot = date_depot;
    }

    public String getHeureDepot() {
        return heureDepot;
    }

    public void setHeureDepot(String heureDepot) {
        this.heureDepot = heureDepot;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<Criteria> criterias) {
        this.criterias = criterias;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_depot='" + date_depot + '\'' +
                ", heureDepot='" + heureDepot + '\'' +
                ", projects=" + projects +
                ", criterias=" + criterias +
                ", runs=" + runs +
                '}';
    }
}
