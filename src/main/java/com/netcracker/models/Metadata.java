package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

// object, mandatory
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata extends AbstractModel {
    // object, mandatory
    @JsonProperty("application")
    private Application application;

    // object, mandatory
    @JsonProperty("description")
    private Description description;

    public Metadata() {

    }

    public Metadata(Application application, Description description) {
        this.application = application;
        this.description = description;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "\"application\": " + application +
                ", \"description\": " + description +
                "}";
    }

    @Override
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("application", application == null);
        checkMap.put("description", description == null);
        return checkMap;
    }
}
