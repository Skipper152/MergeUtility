package com.netcracker.mergemodels;

public class MetadataMerge {
    // "application" field
    // object, mandatory
    private ApplicationMerge applicationMerge;

    // "description" field
    // object, mandatory
    private DescriptionMerge descriptionMerge;

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
}
