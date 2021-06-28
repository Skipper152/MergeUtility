package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;

public class MVNMerge {
    // "groupId" field
    // string, mandatory - groupId of maven artifact
    private final StringWrapper groupId;

    // "artifactId" field
    // string, mandatory - artifactId of maven artifact
    private final StringWrapper artifactId;

    // "version" field
    // string, mandatory - version of maven artifact
    private final StringWrapper version;

    // "service_name" field
    // string, optional
    private final StringWrapper serviceName;

    // "classifier" field
    // string, optional - classifier of maven artifact
    private final StringWrapper classifier;

    // "mvn_type" field
    // string, mandatory - extension of maven artifact
    private final StringWrapper mvnType;

    // "mvn_repository" field
    // string, mandatory
    private final StringWrapper mvnRepository;

    // "hashes" field
    // object, mandatory
    private final HashesMerge hashesMerge;

    public MVNMerge(StringWrapper groupId, StringWrapper artifactId, StringWrapper version,
                    StringWrapper serviceName, StringWrapper classifier, StringWrapper mvnType,
                    StringWrapper mvnRepository, HashesMerge hashesMerge) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.serviceName = serviceName;
        this.classifier = classifier;
        this.mvnType = mvnType;
        this.mvnRepository = mvnRepository;
        this.hashesMerge = hashesMerge;
    }

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
}
