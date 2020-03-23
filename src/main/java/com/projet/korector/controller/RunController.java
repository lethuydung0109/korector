package com.projet.korector.controller;

import com.projet.korector.entity.Run;
import com.projet.korector.model.RunImp;
import com.projet.korector.services.RunService;
import com.projet.korector.services.RunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/run")
public class RunController {
    final static Logger log = LoggerFactory.getLogger(RunController.class);

    @Autowired
    private RunService service;

    @RequestMapping(value = "/createRun", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Run createRun(RunImp run)
    {
        return service.createRun(run);
    }

    @RequestMapping(value = "/allRuns", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Run> getAllRuns()
    {
        return service.getAllRuns();
    }

    @RequestMapping(value = "/allRunByUser/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Run> getRunByUser(@PathVariable Long userId)
    {
        return service.getRunBySession(userId);
    }

    @RequestMapping(value = "/deleteRun/{RunId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRun(@PathVariable Long RunId)
    {
        service.deleteRun(RunId);
    } 
}
