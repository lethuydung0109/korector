package com.projet.korector.entity;

public abstract class CriteriaImpl {

    private String name;
    private String type;
    private  float value;

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
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("name='").append(name).append('\'');
        sb.append("type='").append(type).append('\'');
        sb.append("value='").append(value).append('\'');
        return sb.toString();
    }
}
