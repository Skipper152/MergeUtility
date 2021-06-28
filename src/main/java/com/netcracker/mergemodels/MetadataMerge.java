package com.netcracker.mergemodels;

public class MetadataMerge {
    // "application" field
    // object, mandatory
    private final ApplicationMerge applicationMerge;

    // "description" field
    // object, mandatory
    private final DescriptionMerge descriptionMerge;

    public MetadataMerge(ApplicationMerge applicationMerge, DescriptionMerge descriptionMerge) {
        this.applicationMerge = applicationMerge;
        this.descriptionMerge = descriptionMerge;
    }

    public ApplicationMerge getApplicationMerge() {
        return applicationMerge;
    }

    public DescriptionMerge getDescriptionMerge() {
        return descriptionMerge;
    }
}
