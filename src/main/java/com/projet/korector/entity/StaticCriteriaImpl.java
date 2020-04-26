package com.projet.korector.entity;

import com.projet.korector.entity.CriteriaImpl;

public class StaticCriteriaImpl  extends CriteriaImpl {
    private String url;

    public StaticCriteriaImpl(String url) {
        super();
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StaticCriteriaImpl{");
        sb.append(super.toString());
        sb.append("url='").append(url).append('\'');
        sb.append("}");
        return sb.toString();
    }
}
