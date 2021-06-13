package com.netcracker.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONModel extends AbstractModel {
    // object, mandatory
    @JsonProperty("metadata")
    private Metadata metadata;

    // array, mandatory, can be empty
    @JsonProperty("services")
    private ArrayList<Service> services;

    // array, mandatory, can be empty
    @JsonProperty("artifacts")
    private ArrayList<Artifact> artifacts;

    // array, mandatory, can be empty
    @JsonProperty("script")
    private ArrayList<Script> scripts;

    // object, optional - describes rpm archive
    @JsonProperty("rpm")
    private ArrayList<RPM> rpms;

    // object, mandatory, can be empty
    @JsonProperty("parameters")
    private Parameters parameters;

    public JSONModel() {

    }

    public JSONModel(Metadata metadata, ArrayList<Service> services,
                     ArrayList<Artifact> artifacts, ArrayList<Script> scripts,
                     ArrayList<RPM> rpms, Parameters parameters) {
        this.metadata = metadata;
        this.services = services;
        this.artifacts = artifacts;
        this.scripts = scripts;
        this.rpms = rpms;
        this.parameters = parameters;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public ArrayList<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(ArrayList<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public ArrayList<Script> getScripts() {
        return scripts;
    }

    public void setScripts(ArrayList<Script> scripts) {
        this.scripts = scripts;
    }

    public ArrayList<RPM> getRpms() {
        return rpms;
    }

    public void setRpms(ArrayList<RPM> rpms) {
        this.rpms = rpms;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "{" +
                "\"metadata\": " + metadata +
                ", \"services\": " + services +
                ", \"artifacts\": " + artifacts +
                ", \"script\": " + scripts +
                ", \"rpm\": " + rpms +
                ", \"parameters\"" + parameters +
                "}";
    }

    @Override
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("metadata", metadata == null);
        checkMap.put("services", services == null);
        checkMap.put("artifacts", artifacts == null);
        checkMap.put("script", scripts == null);
        checkMap.put("rpm", rpms == null);
        checkMap.put("parameters", parameters == null);
        return checkMap;
    }
}
