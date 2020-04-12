package com.projet.korector.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CriteriaGroup  implements Serializable {
    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGroup;

    @Column(name ="name")
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
