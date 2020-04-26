package com.projet.korector.entity;


import java.util.ArrayList;
import java.util.List;

public class CriteriaGroupImpl {
    private Long id ;
    private String name;
    private List<CriteriaImpl> criteria;

    public Long getId() {
        return id;
    }

    public CriteriaGroupImpl (Long id, String name) {
        this.id=id;
        this.name = name;
        criteria = new ArrayList<CriteriaImpl>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CriteriaGroup{");
        sb.append(", name='").append(name).append('\'');
        for ( CriteriaImpl c: criteria ) {
            c.toString();
        }
        sb.append('}');
        return sb.toString();
    }
}
