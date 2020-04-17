package com.projet.korector.security.oauth2.user;

import java.util.Map;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getUserName();

    public abstract String getEmail();

    public abstract String getImageUrl();

    public abstract String getHtmlURL();


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getName() + "\n");
        sb.append("Username: " + getUserName() + "\n");
        sb.append("Id: " + getId() + "\n");
        sb.append("Email: " + getEmail() + "\n");
        sb.append("ImageUrl: " + getImageUrl() + "\n");

        return sb.toString();
    }
}