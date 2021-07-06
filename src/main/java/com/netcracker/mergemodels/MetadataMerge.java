package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.Type;

public class MetadataMerge {
    // "application" field
    // object, mandatory
    private ApplicationMerge applicationMerge;

    // "description" field
    // object, mandatory
    private DescriptionMerge descriptionMerge;

    private Type operationType = Type.NONE;

    public ApplicationMerge getApplicationMerge() {
        return applicationMerge;
    }

    public DescriptionMerge getDescriptionMerge() {
        return descriptionMerge;
    }

    public void setApplicationMerge(ApplicationMerge applicationMerge) {
        this.applicationMerge = applicationMerge;
    }

    public void setDescriptionMerge(DescriptionMerge descriptionMerge) {
        this.descriptionMerge = descriptionMerge;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
