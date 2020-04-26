package com.projet.korector.services;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.projet.korector.entity.Project;
import com.projet.korector.entity.Run;
import com.projet.korector.entity.Session;
import com.projet.korector.model.RunImp;
import com.projet.korector.model.User;
import com.projet.korector.repository.RunRepository;
import com.projet.korector.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RunService {

    @Autowired
    private RunRepository runRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    public Run createRun(Long sessionId) {
        Session session = this.sessionRepository.findById(sessionId).get();
        Run run = new Run(session);
        return this.runRepository.save(run);
    }

    public List<Run> getRunBySession(Long sessionId) {

        return null;
    }

    public List<Run> getAllRuns() {
        //return repository.findAll();
        return null;
    }

    public void deleteRun(Long runId) {
        this.runRepository.deleteById(runId);
    }

    public void exportCSV(HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();

        //set file name and content type
        String filename = "run_"+date+".csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        Run run = this.runRepository.findById(1L).get();

            StatefulBeanToCsv<Project> writer = new StatefulBeanToCsvBuilder<Project>(response.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(false)
                    .build();


        //write all users to csv file
//        String r = "rien";
        writer.write((List<Project>) this.sessionService.getSessionProjects(run.getSession().getId()));
    }

}
