package com.projet.korector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SessionCritere implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long critereId;
    private String name;
    private Long height;
    private Float value;
    private Long seuil;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    public SessionCritere() {}

    public SessionCritere(Session session, Long critereId,String name, Long height, Float value, Long seuil,String type) {
        this.session = session;
        this.critereId = critereId;
        this.name=name;
        this.height = height;
        this.value = value;
        this.seuil=seuil;
        this.type=type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Session getSessionId() {
        return session;
    }

    public void setSessionId(Session session) {
        this.session = session;
    }

    public Long getCritereId() {
        return critereId;
    }

    public void setCritereId(Long critereId) {
        this.critereId = critereId;
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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SessionCritere{" +
                "id=" + id +
                ", critereId=" + critereId +
                ", height=" + height +
                ", seuil="+seuil+
                ", value=" + value +
                '}';
    }
}
