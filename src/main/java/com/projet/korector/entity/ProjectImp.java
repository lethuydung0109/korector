package com.projet.korector.entity;

import java.util.Set;

public class ProjectImp {

    private Long id;
    private String name;
    private Float note;
    private Set<SessionImp> listSessions;

    public ProjectImp(Long id, String name, Float note, Set<SessionImp> listSessions) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.listSessions = listSessions;
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

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public Set<SessionImp> getListSessions() {
        return listSessions;
    }

    public void setListSessions(Set<SessionImp> listSessions) {
        this.listSessions = listSessions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectImp{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", note=").append(note);
        sb.append(", listSessions=").append(listSessions);
        sb.append('}');
        return sb.toString();
    }
}
