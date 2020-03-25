package com.projet.korector.repository;

import com.projet.korector.entity.Session;
import com.projet.korector.model.SessionImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
class SessionRepositoryTest {

    @Autowired SessionRepository repository;
    private SessionImp sessionImp;
    private Session session;
    @BeforeEach
    void setUp() {
        sessionImp = new SessionImp("session1");
        session= new Session("sessionEntity");
    }

    @AfterEach
    void tearDown() {
        sessionImp=null;
        session=null;
    }

    @Test
    void save()
    {
        //assertTrue(true);
        Session entity=this.repository.save(this.session);
        System.out.println("Toute le bdd : "+this.repository.findAll());
        assertEquals(entity.getId(),1);
    }
}