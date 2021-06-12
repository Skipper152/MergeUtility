package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.LinkedList;

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
    public String toString() {
        return "{" +
                "\"service_name\": " + serviceName +
                ", \"service_name_1\":" + serviceName1 +
                ", \"service_name_2\":" + serviceName2 +
                '}';
    }

    @Override
    LinkedList<String> validate() {
        LinkedList<String> checkList = new LinkedList<>();
        if (serviceName == null) {
            checkList.addLast("service_name");
        }
        if (serviceName1 == null) {
            checkList.addLast("service_name_1");
        }
        if (serviceName2 == null) {
            checkList.addLast("service_name_2");
        }
        return checkList;
    }
}
