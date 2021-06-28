package com.netcracker.mergemodels;

public class ParametersMerge {
    // "common" field
    // object, optional
    private final CommonMerge commonMerge;

    // "services" field
    // object, optional
    private final ServiceNameMerge services;

    public ParametersMerge(CommonMerge commonMerge, ServiceNameMerge services) {
        this.commonMerge = commonMerge;
        this.services = services;
    }

    public CommonMerge getCommonMerge() {
        return commonMerge;
    }

    public ServiceNameMerge getServices() {
        return services;
    }
}
