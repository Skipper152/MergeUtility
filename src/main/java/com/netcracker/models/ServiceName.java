package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

// object, optional
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceName extends AbstractModel {
    // string, mandatory
    @JsonProperty("service_name")
    private HashMap<String, String> serviceName;

    @JsonProperty("service_name_1")
    private HashMap<String, String> serviceName1;

    @JsonProperty("service_name_2")
    private HashMap<String, String> serviceName2;

    public ServiceName() {

    }

    public ServiceName(HashMap<String, String> serviceName, HashMap<String, String> serviceName1, HashMap<String, String> serviceName2) {
        this.serviceName = serviceName;
        this.serviceName1 = serviceName1;
        this.serviceName2 = serviceName2;
    }

    public HashMap<String, String> getServiceName() {
        return serviceName;
    }

    public void setServiceName(HashMap<String, String> serviceName) {
        this.serviceName = serviceName;
    }

    public HashMap<String, String> getServiceName1() {
        return serviceName1;
    }

    public void setServiceName1(HashMap<String, String> serviceName1) {
        this.serviceName1 = serviceName1;
    }

    public HashMap<String, String> getServiceName2() {
        return serviceName2;
    }

    public void setServiceName2(HashMap<String, String> serviceName2) {
        this.serviceName2 = serviceName2;
    }

    @Override
    public boolean equals(Object model) {

        if (this == model)
            return true;

        if (model == null)
            return false;

        if (this.getClass() != model.getClass())
            return false;

        ServiceName serviceName = (ServiceName) model;

        String someThirdParamFirst = this.serviceName.get("service_name");
        String someThirdParamSecond = serviceName.getServiceName().get("service_name");

        return this.serviceName.equals(serviceName.getSomeParam());
    }

    @Override
    public String toString() {
        return "{" +
                "\"service_name\": " + serviceName +
                ", \"service_name_1\":" + serviceName1 +
                ", \"service_name_2\":" + serviceName2 +
                '}';
    }

    @Override
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("service_name", serviceName == null);
        if (serviceName != null) {
            checkMap.put("some-third-param", !serviceName.containsKey("some-third-param"));
        }
        return checkMap;
    }
}
