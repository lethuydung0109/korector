package com.projet.korector.model;

public class RunImp {

    private Long id;
    private SessionImp session;

    public RunImp(Long id, SessionImp session) {
        this.id = id;
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SessionImp getSession() {
        return session;
    }

    public void setSession(SessionImp session) {
        this.session = session;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RunImp{");
        sb.append("id=").append(id);
        sb.append(", session=").append(session);
        sb.append('}');
        return sb.toString();
    }
}
