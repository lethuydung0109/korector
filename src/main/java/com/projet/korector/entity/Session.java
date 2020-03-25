package com.projet.korector.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Session implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Project> projects;

    @OneToMany
    private Set<Run> runs;


    public Session(Long id, String name) {
        this.id = id;
        this.name = name;
        this.projects= new HashSet<>();
        this.runs = new HashSet<>();
    }

    public Session( String name) {
        this.name = name;
        this.projects= new HashSet<>();
        this.runs = new HashSet<>();
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
        this.projects = projects;
    }

    public Set<Run> getRuns() {
        return runs;
    }

    public void setRuns(Set<Run> runs) {
        this.runs = runs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Session{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", projects=").append(projects);
        sb.append(", runs=").append(runs);
        sb.append('}');
        return sb.toString();
    }
}
