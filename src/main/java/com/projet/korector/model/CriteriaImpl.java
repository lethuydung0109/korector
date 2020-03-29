package com.projet.korector.model;

public abstract class CriteriaImpl {

    private String name;
    private String type;


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("name='").append(name).append('\'');
        sb.append("type='").append(type).append('\'');
        return sb.toString();
    }
}
