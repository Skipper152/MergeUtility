package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;

import java.util.ArrayList;

public class ArtifactMerge {
    // "mvn" field
    // array, mandatory
    private ArrayList<MVNMerge> mvnMerges;

    // "service-short-name" field
    // string, optional
    private StringWrapper serviceShortName;

    // "service_name" field
    // string optional
    private StringWrapper serviceName;

    // "hashes" field
    // object, mandatory
    private HashesMerge hashesMerge;

    // "file" field
    // array, mandatory - list of direct urls. always contains only one element
    private ArrayList<FileMerge> fileMerges;

    // "target_repository" field
    // string, mandatory
    private StringWrapper targetRepository;

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

    public void setMvnMerges(ArrayList<MVNMerge> mvnMerges) {
        this.mvnMerges = mvnMerges;
    }

    public void setServiceShortName(StringWrapper serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public void setServiceName(StringWrapper serviceName) {
        this.serviceName = serviceName;
    }

    public void setHashesMerge(HashesMerge hashesMerge) {
        this.hashesMerge = hashesMerge;
    }

    public void setFileMerges(ArrayList<FileMerge> fileMerges) {
        this.fileMerges = fileMerges;
    }

    public void setTargetRepository(StringWrapper targetRepository) {
        this.targetRepository = targetRepository;
    }
}
