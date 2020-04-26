package com.projet.korector.sonarqube;

import java.util.Map;

/*Interface des fonctionnalités de SonarQube */

public interface SonarQube {
    void traductionMetric(Map<String, String> m);
    Map<String, String> getMetrics(String compName);
}
