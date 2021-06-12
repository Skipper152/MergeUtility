package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

// object, optional - describes rpm archive
@JsonIgnoreProperties(ignoreUnknown = true)
public class RPM extends AbstractModel {
    // string, mandatory - direct url of rpm archive
    @JsonProperty("url")
    private String url;

    // string, mandatory
    @JsonProperty("rpm_repository_name")
    private String rpmRepositoryName;

    // object, mandatory
    @JsonProperty("hashes")
    private Hashes hashes;

    // string optional
    @JsonProperty("service-short-name")
    private String serviceShortName;

    public RPM() {

    }

    public RPM(String url, String rpmRepositoryName, Hashes hashes, String serviceShortName) {
        this.url = url;
        this.rpmRepositoryName = rpmRepositoryName;
        this.hashes = hashes;
        this.serviceShortName = serviceShortName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRpmRepositoryName() {
        return rpmRepositoryName;
    }

    public void setRpmRepositoryName(String rpmRepositoryName) {
        this.rpmRepositoryName = rpmRepositoryName;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    public String getServiceShortName() {
        return serviceShortName;
    }

    public void setServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    @Override
    public String toString() {
        return "{" +
                "\"url\": " + "\"" + url + "\"" +
                ", \"rpmRepositoryName\": " + "\"" + rpmRepositoryName + "\"" +
                ", \"hashes\": " + hashes +
                ", \"serviceShortName\": " + "\"" + serviceShortName + "\"" +
                "}";
    }

    @Override
    LinkedList<String> validate() {
        LinkedList<String> checkList = new LinkedList<>();
        if (url == null) {
            checkList.addLast("url");
        }
        if (rpmRepositoryName == null) {
            checkList.addLast("rpm_repository_name");
        }
        if (hashes == null) {
            checkList.addLast("hashes");
        }
        return checkList;
    }
}
