package com.projet.korector.jenkins;

import com.offbytwo.jenkins.model.Job;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/*Interface des fonctionnalités de Jenkins*/
@Service
public interface JenkinsService {
    boolean isJobExist(String name);
    String getJobXml(String name);
    String buildJob(String name, boolean isCreation);
    String createJob(String name, String xml);
    String deleteJob(String name);
    String getResultLasBuild(String jobName);
    String getOutPut(String jobName);
    boolean waitForBuildToComplete(long timeOut, String jobName, int numBuildExpected, boolean isCreation) throws InterruptedException, TimeoutException, IOException;
    int getLastBuildNumber(String jobName);
    Map<String, Job> getListJob();
}
