package com.projet.korector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projet.korector.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="sessions_projects",
            joinColumns = {
                    @JoinColumn(name = "session_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})

    private Set<Project> projects = new HashSet<>();

    @ManyToMany(mappedBy = "sessions",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users = new HashSet<>();


    @OneToMany
    @JsonIgnore
    private Set<Run> runs;

    public Session() { }

    public Session(String n, String date) {
        this.name = n;
        this.date_depot=date;
        this.runs = new HashSet<>();

    }

//    public Session(String n, String date, Set<Project> projects) {
//        this.name = n;
//        this.projects= projects;
//        this.projects= new HashSet<>();
//        this.runs = new HashSet<>();
//        this.date_depot=date;
//    }

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
        this.projects = projects;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_depot='" + date_depot + '\'' +
                ", projects=" + projects +
                ", runs=" + runs +
                '}';
    }
}
