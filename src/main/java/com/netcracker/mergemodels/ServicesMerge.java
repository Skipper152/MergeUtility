package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.ServiceStringWrapper;
import com.netcracker.mergemodels.wrapper.Type;

import java.util.ArrayList;

public class ServicesMerge {
    // "service_name" field
    // string, mandatory
    private ArrayList<ServiceStringWrapper> serviceName;

    // "service_name_1" field
    private ArrayList<ServiceStringWrapper> serviceName1;

    // "service_name_2" field
    private ArrayList<ServiceStringWrapper> serviceName2;

    private Type operationType = Type.NONE;

    public ArrayList<ServiceStringWrapper> getServiceName() {
        return serviceName;
    }

    public ArrayList<ServiceStringWrapper> getServiceName1() {
        return serviceName1;
    }

    public ArrayList<ServiceStringWrapper> getServiceName2() {
        return serviceName2;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setServiceName(ArrayList<ServiceStringWrapper> serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceName1(ArrayList<ServiceStringWrapper> serviceName1) {
        this.serviceName1 = serviceName1;
    }

    public void setServiceName2(ArrayList<ServiceStringWrapper> serviceName2) {
        this.serviceName2 = serviceName2;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
