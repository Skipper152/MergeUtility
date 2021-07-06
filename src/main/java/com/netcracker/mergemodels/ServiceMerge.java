package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.BooleanWrapper;
import com.netcracker.mergemodels.wrapper.StringWrapper;
import com.netcracker.mergemodels.wrapper.Type;

public class ServiceMerge {
    // "service-short-name" field
    // string, optional
    private StringWrapper serviceShortName;

    // "service_name" field
    // string, mandatory
    private StringWrapper serviceName;

    // "artifact_type" field
    // string, mandatory
    private StringWrapper artifactType;

    // "docker_registry" field
    // string, mandatory
    private StringWrapper dockerRegistry;

    // "docker_image_name" field
    // string, mandatory
    private StringWrapper dockerImageName;

    // "docker_tag" field
    // string, mandatory
    private StringWrapper dockerTag;

    // "force" field
    // boolean, optional
    private BooleanWrapper force;

    // "github_repository" field
    // string, optional
    private StringWrapper githubRepository;

    // "github_branch" field
    // string, optional
    private StringWrapper githubBranch;

    // "github_hash" field
    // string, optional
    private StringWrapper githubHash;

    // "hashes" field
    // object, mandatory
    private HashesMerge hashesMerge;

    private Type operationType = Type.NONE;

    public StringWrapper getServiceShortName() {
        return serviceShortName;
    }

    public StringWrapper getServiceName() {
        return serviceName;
    }

    public StringWrapper getArtifactType() {
        return artifactType;
    }

    public StringWrapper getDockerRegistry() {
        return dockerRegistry;
    }

    public StringWrapper getDockerImageName() {
        return dockerImageName;
    }

    public StringWrapper getDockerTag() {
        return dockerTag;
    }

    public BooleanWrapper getForce() {
        return force;
    }

    public StringWrapper getGithubRepository() {
        return githubRepository;
    }

    public StringWrapper getGithubBranch() {
        return githubBranch;
    }

    public StringWrapper getGithubHash() {
        return githubHash;
    }

    public HashesMerge getHashesMerge() {
        return hashesMerge;
    }

    public void setServiceShortName(StringWrapper serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public void setServiceName(StringWrapper serviceName) {
        this.serviceName = serviceName;
    }

    public void setArtifactType(StringWrapper artifactType) {
        this.artifactType = artifactType;
    }

    public void setDockerRegistry(StringWrapper dockerRegistry) {
        this.dockerRegistry = dockerRegistry;
    }

    public void setDockerImageName(StringWrapper dockerImageName) {
        this.dockerImageName = dockerImageName;
    }

    public void setDockerTag(StringWrapper dockerTag) {
        this.dockerTag = dockerTag;
    }

    public void setForce(BooleanWrapper force) {
        this.force = force;
    }

    public void setGithubRepository(StringWrapper githubRepository) {
        this.githubRepository = githubRepository;
    }

    public void setGithubBranch(StringWrapper githubBranch) {
        this.githubBranch = githubBranch;
    }

    public void setGithubHash(StringWrapper githubHash) {
        this.githubHash = githubHash;
    }

    public void setHashesMerge(HashesMerge hashesMerge) {
        this.hashesMerge = hashesMerge;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
