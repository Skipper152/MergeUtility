package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Parameters extends AbstractModel {
    // object, optional
    @JsonProperty("common")
    private Common common;

    // object, optional
    @JsonProperty("services")
    private ServiceName services;

    public Parameters() {

    }

    public Parameters(Common common, ServiceName services) {
        this.common = common;
        this.services = services;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public ServiceName getServices() {
        return services;
    }

    public void setServices(ServiceName services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "{" +
                "\"common\": " + common +
                ", \"services\": " + services +
                '}';
    }

    @Override
    public HashMap<String, Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("common", common == null);
        checkMap.put("services", services == null);
        return checkMap;
    }
}
