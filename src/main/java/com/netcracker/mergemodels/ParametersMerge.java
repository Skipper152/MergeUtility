package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.Type;

public class ParametersMerge {
    // "common" field
    // object, optional
    private CommonMerge commonMerge;

    // "services" field
    // object, optional
    private ServicesMerge services;

    private Type operationType = Type.NONE;

    public CommonMerge getCommonMerge() {
        return commonMerge;
    }

    public ServicesMerge getServices() {
        return services;
    }

    public void setCommonMerge(CommonMerge commonMerge) {
        this.commonMerge = commonMerge;
    }

    public void setServices(ServicesMerge services) {
        this.services = services;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
