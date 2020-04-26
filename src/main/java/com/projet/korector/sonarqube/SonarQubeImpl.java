package com.projet.korector.sonarqube;
import com.google.gson.JsonElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.boot.json.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



import static com.projet.korector.jenkins.constants.*;

/* Implémentation des fonctionnalités de Sonarqube pour récupérer et exploiter les métriques. */

public class SonarQubeImpl implements SonarQube {

    private Map<String, String> m;

    public SonarQubeImpl() {
        this.m = new HashMap<>();
        traductionMetric(this.m);
    }

    //Les principales métriques
    public String askedMetrics = "bugs,confirmed_issues,code_smells,sqale_index,coverage,duplicated_lines_density,duplicated_blocks";

    public String getAskedMetrics() {
        return askedMetrics;
    }

    //traduction des noms de métriques
    public void traductionMetric(Map<String, String> m) {
        m.put("bugs", "nombre de bugs");
        m.put("confirmed_issues", "vulnérabilités");
        m.put("code_smells", "code smells");
        m.put("sqale_index", "debt");
        m.put("coverage","coverage");
        m.put("duplicated_lines_density", "duplications");
        m.put("duplicated_blocks", "blocs dupliqués");
    }

    //Retourne l'ensemble des métriques sonar en fonction de la clé d'un projet
    public Map<String, String> getMetrics(String compName) throws RuntimeException {
        try {
            URL url = new URL(URL_SONARQUBE + "api/measures/component?metricKeys=" + getAskedMetrics() + "&component=" + compName + "");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("hello", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output = br.readLine();
            JsonObject js=new JsonParser().parse(output).getAsJsonObject();
            JsonArray metrics = js.getAsJsonObject("component").getAsJsonArray("measures");

            Map<String, String> map = new HashMap<>();
            String name;
            String value;
            for(JsonElement metric : metrics) {
                name = metric.getAsJsonObject().get("metric").getAsString();
                value = metric.getAsJsonObject().get("value").getAsString();
                map.put(this.m.get(name), value);
            }
            System.out.println(map);
            conn.disconnect();
            return map;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Collections.singletonMap("ERROR",e.getMessage());
        }
    }
}
