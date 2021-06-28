package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;

import java.util.ArrayList;

public class ArtifactMerge {
    // "mvn" field
    // array, mandatory
    private final ArrayList<MVNMerge> mvnMerges;

    // "service-short-name" field
    // string, optional
    private final StringWrapper serviceShortName;

    // "service_name" field
    // string optional
    private final StringWrapper serviceName;

    // "hashes" field
    // object, mandatory
    private final HashesMerge hashesMerge;

    // "file" field
    // array, mandatory - list of direct urls. always contains only one element
    private final ArrayList<FileMerge> fileMerges;

    // "target_repository" field
    // string, mandatory
    private final StringWrapper targetRepository;

    public ArtifactMerge(ArrayList<MVNMerge> mvnMerges, StringWrapper serviceShortName, StringWrapper serviceName,
                         HashesMerge hashesMerge, ArrayList<FileMerge> fileMerges, StringWrapper targetRepository) {
        this.mvnMerges = mvnMerges;
        this.serviceShortName = serviceShortName;
        this.serviceName = serviceName;
        this.hashesMerge = hashesMerge;
        this.fileMerges = fileMerges;
        this.targetRepository = targetRepository;
    }

    public ArrayList<MVNMerge> getMvnMerges() {
        return mvnMerges;
    }

    public StringWrapper getServiceShortName() {
        return serviceShortName;
    }

    public StringWrapper getServiceName() {
        return serviceName;
    }

    public HashesMerge getHashesMerge() {
        return hashesMerge;
    }

    public ArrayList<FileMerge> getFileMerges() {
        return fileMerges;
    }

    public StringWrapper getTargetRepository() {
        return targetRepository;
    }
}
