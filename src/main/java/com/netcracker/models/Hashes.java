package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

// object, mandatory
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hashes extends AbstractModel {
    // string, mandatory
    @JsonProperty("sha1")
    private String sha1;

    // string, mandatory
    @JsonProperty("sha256")
    private String sha256;

    public Hashes() {

    }

    public Hashes(String sha1, String sha256) {
        this.sha1 = sha1;
        this.sha256 = sha256;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    @Override
    public String toString() {
        return "{" +
                "\"sha1\": " + "\"" + sha1 + "\"" +
                ", \"sha256\": " + "\"" + sha256 + "\"" +
                "}";
    }

    @Override
    public boolean equals(Object model) {

        if (this == model)
            return true;

        if (model == null)
            return false;

        if (this.getClass() != model.getClass())
            return false;

        Hashes hashes = (Hashes) model;

        if (this.sha1 != null & this.sha256 != null) {
            return sha1.equals(hashes.getSha1()) &&
                    sha256.equals(hashes.getSha256());
        } else if (this.sha1 != null && hashes.getSha256() == null) {
            return sha1.equals(hashes.getSha1());
        } else if (this.sha256 != null && hashes.getSha1() == null) {
            return sha256.equals(hashes.getSha256());
        }

        return false;
    }

    @Override
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("sha1", sha1 == null);
        checkMap.put("sha256", sha256 == null);
        return checkMap;
    }
}
