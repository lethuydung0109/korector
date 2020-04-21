package com.projet.korector.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "run")
public class Run implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    public Run () {};

    public Run(Session session) {
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Run{");
        sb.append("id=").append(id);
        sb.append(", session=").append(session);
        sb.append('}');
        return sb.toString();
    }
}
