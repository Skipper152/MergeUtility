package com.netcracker.mergemodels2;

import com.netcracker.mergemodels2.wrapper.Type;

public class ParametersMerge {
    // "common" field
    // object, optional
    private CommonMerge commonMerge;

    // "services" field
    // object, optional
    private ServiceNameMerge services;

    private Type operationType = Type.NONE;

    public CommonMerge getCommonMerge() {
        return commonMerge;
    }

    public ServiceNameMerge getServices() {
        return services;
    }

    public void setCommonMerge(CommonMerge commonMerge) {
        this.commonMerge = commonMerge;
    }

    public void setServices(ServiceNameMerge services) {
        this.services = services;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
