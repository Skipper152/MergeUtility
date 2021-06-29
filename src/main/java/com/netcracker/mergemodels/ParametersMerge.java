package com.netcracker.mergemodels;

public class ParametersMerge {
    // "common" field
    // object, optional
    private CommonMerge commonMerge;

    // "services" field
    // object, optional
    private ServiceNameMerge services;

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
}
