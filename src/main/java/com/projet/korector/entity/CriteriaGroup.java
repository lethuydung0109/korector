package com.projet.korector.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CriteriaGroup {
    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    private Long idGroup;
    private String name;

    public CriteriaGroup(Long idGroup, String name) {
        this.idGroup = idGroup;
        this.name = name;
    }
    public Long getIdGroup() {
        return idGroup;
    }

    public String getName() {
        return name;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CriteriaGroup{");
        sb.append(", idGroup='").append(idGroup).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
