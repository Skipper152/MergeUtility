package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

// object, mandatory
@JsonIgnoreProperties(ignoreUnknown = true)
public class Application extends AbstractModel {
    // string, mandatory
    @JsonProperty("name")
    private String name;

    public Application() {
    }

    public Application(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\": " + "\"" + name + "\"" +
                "}";
    }

    @Override
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("name", name == null);
        return checkMap;
    }

}
