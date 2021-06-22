package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

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
    public boolean equals(Object model) {

        if (this == model)
            return true;

        if (model == null)
            return false;

        if (this.getClass() != model.getClass())
            return false;

        Script script = (Script) model;

        return scriptName.equals(script.getScriptName()) &&
                hashes.equals(script.getHashes()) &&
                url.equals(script.getUrl());
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
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("script_name", scriptName == null);
        checkMap.put("hashes", hashes == null);
        checkMap.put("url", url == null);
        return checkMap;
    }
}
