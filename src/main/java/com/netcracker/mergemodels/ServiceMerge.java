package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.BooleanWrapper;
import com.netcracker.mergemodels.wrapper.StringWrapper;

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

    public ServiceMerge(StringWrapper serviceShortName, StringWrapper serviceName, StringWrapper artifactType,
                        StringWrapper dockerRegistry, StringWrapper dockerImageName, StringWrapper dockerTag,
                        BooleanWrapper force, StringWrapper githubRepository, StringWrapper githubBranch,
                        StringWrapper githubHash, HashesMerge hashesMerge) {
        this.serviceShortName = serviceShortName;
        this.serviceName = serviceName;
        this.artifactType = artifactType;
        this.dockerRegistry = dockerRegistry;
        this.dockerImageName = dockerImageName;
        this.dockerTag = dockerTag;
        this.force = force;
        this.githubRepository = githubRepository;
        this.githubBranch = githubBranch;
        this.githubHash = githubHash;
        this.hashesMerge = hashesMerge;
    }

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
}
