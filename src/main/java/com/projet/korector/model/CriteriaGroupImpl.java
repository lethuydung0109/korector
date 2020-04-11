package com.projet.korector.model;


import java.util.ArrayList;
import java.util.List;

public class CriteriaGroupImpl {
    private String name;
    private List<CriteriaImpl> Criteria;

    public CriteriaGroupImpl(Long idGroup, String name) {
        this.name = name;
        Criteria = new ArrayList<CriteriaImpl>();
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
        sb.append('}');
        return sb.toString();
    }
}
