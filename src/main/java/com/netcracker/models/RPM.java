package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

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
    public boolean equals(Object model) {

        if (this == model)
            return true;

        if (model == null)
            return false;

        if (this.getClass() != model.getClass())
            return false;

        RPM rpm = (RPM) model;
        return url.equals(rpm.getUrl()) &&
                rpmRepositoryName.equals(rpm.getRpmRepositoryName()) &&
                hashes.equals(rpm.getHashes());
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
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("url", url == null);
        checkMap.put("rpm_repository_name", rpmRepositoryName == null);
        checkMap.put("hashes", hashes == null);
        return checkMap;
    }
}
