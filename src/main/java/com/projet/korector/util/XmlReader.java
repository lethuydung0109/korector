package com.projet.korector.util;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XmlReader {



    String url;
    String key;
    String keyProperties;
    String properties;

    public XmlReader( String url,String properties){
        this.url=url;

        this.properties = properties;
    }

    public String formatXML(){
        try{
            String filePath= "src/main/java/com/projet/korector/jenkins/job.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);

            Node url = doc.getElementsByTagName("url").item(0);
            url.setTextContent(this.url);

            Node properties = doc.getElementsByTagName("properties").item(1);
            System.out.println("Text context of properties avnat " + properties.getTextContent());
            properties.setTextContent(this.properties);
            System.out.println("Text context of properties apres" + properties.getTextContent());


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            System.out.println("Done");

            return FileUtils.readFileToString(new File(filePath));

        } catch(Exception e ){
            e.printStackTrace();
        }
        return null;
        /*try {
            //String fileName = "src/main/ressources/job";
            //BufferedReader fr = new BufferedReader(new FileReader((fileName)));
            //String xml = "";
            //String line;
            /*while ((line = fr.readLine()) != null) {
                //System.out.priﬁntln(xml);
                xml += line;
            }
            if(xml.contains(key)){
                xml= xml.substring(0,xml.indexOf(key))+url+xml.substring(xml.indexOf(key));
            }
            System.out.println(xml);
            return xml;
        /*}catch (
                FileNotFoundException ex){
            System.out.println("Fichier introuvable");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;*/
    }

}
