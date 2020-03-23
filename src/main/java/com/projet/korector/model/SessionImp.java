package com.projet.korector.model;

import com.projet.korector.entity.Project;

import java.util.HashSet;
import java.util.Set;

public class SessionImp {

    private Long id;
    private String name;
    private Set<Project> projects;
    //private User user;


    public SessionImp(String name) {
        this.name = name;
        this.projects = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SessionImp{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
