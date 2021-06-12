package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

// object, optional
@JsonIgnoreProperties(ignoreUnknown = true)
public class Script extends AbstractModel {
    // string, optional
    @JsonProperty("service-short-name")
    private String serviceShortName;

    // string, optional
    @JsonProperty("start-point")
    private String startPoint;

    // string, optional
    @JsonProperty("end-point")
    private String endPoint;

    // string, mandatory
    @JsonProperty("script_name")
    private String scriptName;

    // object, mandatory
    @JsonProperty("hashes")
    private Hashes hashes;

    // string, mandatory - direct url of archive with script
    @JsonProperty("url")
    private String url;

    public Script() {

    }

    public Script(String serviceShortName, String startPoint, String endPoint,
                  String scriptName, Hashes hashes, String url) {
        this.serviceShortName = serviceShortName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.scriptName = scriptName;
        this.hashes = hashes;
        this.url = url;
    }

    public String getServiceShortName() {
        return serviceShortName;
    }

    public void setServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "{" +
                "\"serviceShortName\": " + "\"" + serviceShortName + "\"" +
                ", \"startPoint\": " + "\"" + startPoint + "\"" +
                ", \"endPoint\": " + "\"" + endPoint + "\"" +
                ", \"scriptName\": " + "\"" + scriptName + "\"" +
                ", \"hashes\": " + hashes +
                ", \"url\": " + "\"" + url + "\"" +
                "}";
    }

    @Override
    LinkedList<String> validate() {
        LinkedList<String> checkList = new LinkedList<>();
        if (scriptName == null) {
            checkList.addLast("script_name");
        }
        if (hashes == null) {
            checkList.addLast("hashes");
        }
        if (url == null) {
            checkList.addLast("url");
        }
        return checkList;
    }
}
