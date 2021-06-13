package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

// object, mandatory
@JsonIgnoreProperties(ignoreUnknown = true)
public class Description extends AbstractModel {
    // integer, mandatory
    @JsonProperty("version")
    private int version;

    public Description() {

    }

    public Description(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "{" +
                "\"version\": " + version +
                "}";
    }

    @Override
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("version", version == 0);
        return checkMap;
    }
}
