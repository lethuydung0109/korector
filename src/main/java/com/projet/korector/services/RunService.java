package com.projet.korector.services;

import com.projet.korector.entity.Run;
import com.projet.korector.model.RunImp;
import com.projet.korector.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunService {

    @Autowired
    private RunRepository repository;

    public Run createRun(RunImp run) {

        // return repository.save(run);
        return null;
    }

    public List<Run> getRunBySession(Long sessionId) {

        return null;
    }

    public List<Run> getAllRuns() {
        //return repository.findAll();
        return null;
    }

    public void deleteRun(Long runId) {
        repository.deleteById(runId);
    }

}
