package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.LinkedList;

// object, optional
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artifact extends AbstractModel {
    // array, mandatory // вопрос! что тут за струткура данных?!
    @JsonProperty("mvn")
    private ArrayList<MVN> mvns;

    // string, optional
    @JsonProperty("service-short-name")
    private String serviceShortName;
//sdfsdf
    // string optional
    @JsonProperty("service_name")
    private String serviceName;

    // object, mandatory
    @JsonProperty("hashes")
    private Hashes hashes;

    // array, mandatory - list of direct urls. always contains only one element
    @JsonProperty("file")
    private ArrayList<File> files;

    // string, mandatory
    @JsonProperty("target_repository")
    private String targetRepository;

    public Artifact() {

    }

    public Artifact(ArrayList<MVN> mvns, String serviceShortName,
                    String serviceName, Hashes hashes, String targetRepository) {
        this.mvns = mvns;
        this.serviceShortName = serviceShortName;
        this.serviceName = serviceName;
        this.hashes = hashes;
        this.targetRepository = targetRepository;
    }

    public ArrayList<MVN> getMvns() {
        return mvns;
    }

    public void setMvns(ArrayList<MVN> mvns) {
        this.mvns = mvns;
    }

    public String getServiceShortName() {
        return serviceShortName;
    }

    public void setServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public String getTargetRepository() {
        return targetRepository;
    }

    public void setTargetRepository(String targetRepository) {
        this.targetRepository = targetRepository;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "{" +
                "\"mvn\":" + mvns +
                ", \"serviceShortName\": " + "\"" + serviceShortName + "\"" +
                ", \"serviceName\": " + "\"" + serviceName + "\"" +
                ", \"hashes\": " + hashes +
                ", \"file\": " + files +
                ", \"targetRepository\": " + "\"" + targetRepository + "\"" +
                "}";
    }

    @Override
    LinkedList<String> validate() {
        LinkedList<String> checkList = new LinkedList<>();
        if (mvns == null) {
            checkList.addLast("mvn");
        }
        if (hashes == null) {
            checkList.addLast("hashes");
        }
        if (files == null) {
            checkList.addLast("file");
        }
        if (targetRepository == null) {
            checkList.addLast("target_repository");
        }
        return checkList;
    }
}
