package com.projet.korector.entity;

public class SessionCritereImp {

    private Long id;
    private Long critereId;
    private Long sessionId;
    private String name;
    private Long height;
    private Float value;
    private Long seuil;
    private String type;

    public SessionCritereImp(Long critereId, Long sessionId, String name,Long height, Float value, Long seuil,String type) {
        this.critereId = critereId;
        this.sessionId = sessionId;
        this.name=name;
        this.height = height;
        this.value = value;
        this.seuil = seuil;
        this.type=type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCritereId() {
        return critereId;
    }

    public void setCritereId(Long critereId) {
        this.critereId = critereId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Long getSeuil() {
        return seuil;
    }

    public void setSeuil(Long seuil) {
        this.seuil = seuil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SessionCritereImp{" +
                "id=" + id +
                ", critereId=" + critereId +
                ", sessionId=" + sessionId +
                ", height=" + height +
                ", value=" + value +
                ", seuil=" + seuil +
                '}';
    }
}
