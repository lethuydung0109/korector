package com.projet.korector.payload.response;

import com.projet.korector.entity.User;

import java.util.List;

public class StatistiqueResponse {
    int nb_students;
    int nb_profs;
    int nb_classes;
    List <User> users;

    public StatistiqueResponse(int nb_students, int nb_profs, int nb_classes) {
        this.nb_students = nb_students;
        this.nb_profs = nb_profs;
        this.nb_classes = nb_classes;
    }


    public int getNb_students() {
        return nb_students;
    }

    public void setNb_students(int nb_students) {
        this.nb_students = nb_students;
    }

    public int getNb_profs() {
        return nb_profs;
    }

    public void setNb_profs(int nb_profs) {
        this.nb_profs = nb_profs;
    }

    public int getNb_classes() {
        return nb_classes;
    }

    public void setNb_classes(int nb_classes) {
        this.nb_classes = nb_classes;
    }
}
