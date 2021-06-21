package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

// object, optional
@JsonIgnoreProperties(ignoreUnknown = true)
public class Service extends AbstractModel {
    // string, optional
    @JsonProperty("service-short-name")
    private String serviceShortName;

    // string, mandatory
    @JsonProperty("service_name")
    private String serviceName;

    // string, mandatory
    @JsonProperty("artifact_type")
    private String artifactType;

    // string, mandatory
    @JsonProperty("docker_registry")
    private String dockerRegistry;

    // string, mandatory
    @JsonProperty("docker_image_name")
    private String dockerImageName;

    // string, mandatory
    @JsonProperty("docker_tag")
    private String dockerTag;

    // boolean, optional
    @JsonProperty("force")
    private Boolean force;

    // string, optional
    @JsonProperty("github_repository")
    private String githubRepository;

    // string, optional
    @JsonProperty("github_branch")
    private String githubBranch;

    // string, optional
    @JsonProperty("github_hash")
    private String githubHash;

    // object, mandatory
    @JsonProperty("hashes")
    private Hashes hashes;

    public Service() {

    }

    public Service(String serviceShortName, String serviceName,
                   String artifactType, String dockerRegistry,
                   String dockerImageName, String dockerTag,
                   boolean force, String githubRepository,
                   String githubBranch, String githubHash,
                   Hashes hashes) {
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
        this.hashes = hashes;
    }

    public String getServiceShortName() {
        return serviceShortName;
    }

    public void setServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getArtifactType() {
        return artifactType;
    }

    public void setArtifactType(String artifactType) {
        this.artifactType = artifactType;
    }

    public String getDockerRegistry() {
        return dockerRegistry;
    }

    public void setDockerRegistry(String dockerRegistry) {
        this.dockerRegistry = dockerRegistry;
    }

    public String getDockerImageName() {
        return dockerImageName;
    }

    public void setDockerImageName(String dockerImageName) {
        this.dockerImageName = dockerImageName;
    }

    public String getDockerTag() {
        return dockerTag;
    }

    public void setDockerTag(String dockerTag) {
        this.dockerTag = dockerTag;
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public String getGithubRepository() {
        return githubRepository;
    }

    public void setGithubRepository(String githubRepository) {
        this.githubRepository = githubRepository;
    }

    public String getGithubBranch() {
        return githubBranch;
    }

    public void setGithubBranch(String githubBranch) {
        this.githubBranch = githubBranch;
    }

    public String getGithubHash() {
        return githubHash;
    }

    public void setGithubHash(String githubHash) {
        this.githubHash = githubHash;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

    @Override
    public String toString() {
        return "{" +
                "\"serviceShortName\": " + "\"" + serviceShortName + "\"" +
                ", \"serviceName\": " + "\"" + serviceName + "\"" +
                ", \"artifactType\": " + "\"" + artifactType + "\"" +
                ", \"dockerRegistry\": " + "\"" + dockerRegistry + "\"" +
                ", \"dockerImageName\": " + "\"" + dockerImageName + "\"" +
                ", \"dockerTag\": " + "\"" + dockerTag + "\"" +
                ", \"force\": " + force +
                ", \"githubRepository\": " + "\"" + githubRepository + "\"" +
                ", \"githubBranch\": " + "\"" + githubBranch + "\"" +
                ", \"githubHash\": " + "\"" + githubHash + "\"" +
                ", \"hashes\": " + hashes +
                "}";
    }

    @Override
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("service_name", serviceName == null);
        checkMap.put("artifact_type", artifactType == null);
        checkMap.put("docker_registry", dockerRegistry == null);
        checkMap.put("docker_image_name", dockerImageName == null);
        checkMap.put("docker_tag", dockerTag == null);
        checkMap.put("hashes", hashes == null);
        return checkMap;
    }
}
