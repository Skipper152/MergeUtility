package com.netcracker.mergemodels2;

import com.netcracker.mergemodels2.wrapper.StringWrapper;
import com.netcracker.mergemodels2.wrapper.Type;

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

    private Integer mvn = 0;

    private Type operationType = Type.NONE;

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

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }

    public Integer getMVN() {
        return mvn;
    }

    public void setMVN(Integer mvn) {
        this.mvn = mvn;
    }
}
