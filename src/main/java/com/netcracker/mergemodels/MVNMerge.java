package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;

public class MVNMerge {
    // "groupId" field
    // string, mandatory - groupId of maven artifact
    private StringWrapper groupId;

    // "artifactId" field
    // string, mandatory - artifactId of maven artifact
    private StringWrapper artifactId;

    // "version" field
    // string, mandatory - version of maven artifact
    private StringWrapper version;

    // "service_name" field
    // string, optional
    private StringWrapper serviceName;

    // "classifier" field
    // string, optional - classifier of maven artifact
    private StringWrapper classifier;

    // "mvn_type" field
    // string, mandatory - extension of maven artifact
    private StringWrapper mvnType;

    // "mvn_repository" field
    // string, mandatory
    private StringWrapper mvnRepository;

    // "hashes" field
    // object, mandatory
    private HashesMerge hashesMerge;

    public StringWrapper getGroupId() {
        return groupId;
    }

    public StringWrapper getArtifactId() {
        return artifactId;
    }

    public StringWrapper getVersion() {
        return version;
    }

    public StringWrapper getServiceName() {
        return serviceName;
    }

    public StringWrapper getClassifier() {
        return classifier;
    }

    public StringWrapper getMvnType() {
        return mvnType;
    }

    public StringWrapper getMvnRepository() {
        return mvnRepository;
    }

    public HashesMerge getHashesMerge() {
        return hashesMerge;
    }

    public void setGroupId(StringWrapper groupId) {
        this.groupId = groupId;
    }

    public void setArtifactId(StringWrapper artifactId) {
        this.artifactId = artifactId;
    }

    public void setVersion(StringWrapper version) {
        this.version = version;
    }

    public void setServiceName(StringWrapper serviceName) {
        this.serviceName = serviceName;
    }

    public void setClassifier(StringWrapper classifier) {
        this.classifier = classifier;
    }

    public void setMvnType(StringWrapper mvnType) {
        this.mvnType = mvnType;
    }

    public void setMvnRepository(StringWrapper mvnRepository) {
        this.mvnRepository = mvnRepository;
    }

    public void setHashesMerge(HashesMerge hashesMerge) {
        this.hashesMerge = hashesMerge;
    }
}
