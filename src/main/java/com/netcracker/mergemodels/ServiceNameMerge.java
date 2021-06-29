package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;

import java.util.HashMap;

public class ServiceNameMerge {
    // "service_name" field
    // string, mandatory
    private HashMap<StringWrapper, StringWrapper> serviceName;

    // "service_name_1" field
    private HashMap<StringWrapper, StringWrapper> serviceName1;

    // "service_name_2" field
    private HashMap<StringWrapper, StringWrapper> serviceName2;

    public HashMap<StringWrapper, StringWrapper> getServiceName() {
        return serviceName;
    }

    public HashMap<StringWrapper, StringWrapper> getServiceName1() {
        return serviceName1;
    }

    public HashMap<StringWrapper, StringWrapper> getServiceName2() {
        return serviceName2;
    }

    public void setServiceName(HashMap<StringWrapper, StringWrapper> serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceName1(HashMap<StringWrapper, StringWrapper> serviceName1) {
        this.serviceName1 = serviceName1;
    }

    public void setServiceName2(HashMap<StringWrapper, StringWrapper> serviceName2) {
        this.serviceName2 = serviceName2;
    }
}
