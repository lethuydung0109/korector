package com.projet.korector.entity;

import javax.persistence.*;

@Entity
@Table( name ="seuilCriteria")
public class seuilCriteria {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long criteriaName;

    @Column
    private Long value;

    @Column

    private String session_id;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }


}
