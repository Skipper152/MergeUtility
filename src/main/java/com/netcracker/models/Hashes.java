package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

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
    LinkedList<String> validate() {
        LinkedList<String> checkList = new LinkedList<>();
        if (sha1 == null) {
            checkList.addLast("sha1");
        }
        if (sha256 == null) {
            checkList.addLast("sha256");
        }
        return checkList;
    }
}
