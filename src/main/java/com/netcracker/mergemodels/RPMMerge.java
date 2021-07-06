package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;
import com.netcracker.mergemodels.wrapper.Type;

public class RPMMerge {
    // "url" field
    // string, mandatory - direct url of rpm archive
    private StringWrapper url;

    // "rpm_repository_name" field
    // string, mandatory
    private StringWrapper rpmRepositoryName;

    // "hashes" field
    // object, mandatory
    private HashesMerge hashesMerge;

    // "service-short-name" field
    // string optional
    private StringWrapper serviceShortName;

    private Type operationType = Type.NONE;

    public StringWrapper getUrl() {
        return url;
    }

    public StringWrapper getRpmRepositoryName() {
        return rpmRepositoryName;
    }

    public HashesMerge getHashes() {
        return hashesMerge;
    }

    public StringWrapper getServiceShortName() {
        return serviceShortName;
    }

    public void setUrl(StringWrapper url) {
        this.url = url;
    }

    public void setRpmRepositoryName(StringWrapper rpmRepositoryName) {
        this.rpmRepositoryName = rpmRepositoryName;
    }

    public void setHashesMerge(HashesMerge hashesMerge) {
        this.hashesMerge = hashesMerge;
    }

    public void setServiceShortName(StringWrapper serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public HashesMerge getHashesMerge() {
        return hashesMerge;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
