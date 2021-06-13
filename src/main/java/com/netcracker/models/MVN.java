package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.LinkedList;

// object, mandatory
@JsonIgnoreProperties(ignoreUnknown = true)
public class MVN extends AbstractModel {
    // string, mandatory - groupId of maven artifact
    @JsonProperty("groupId")
    private String groupId;

    // string, mandatory - artifactId of maven artifact
    @JsonProperty("artifactId")
    private String artifactId;

    // string, mandatory - version of maven artifact
    @JsonProperty("version")
    private String version;

    // string, optional
    @JsonProperty("service_name")
    private String serviceName;

    // string, optional - classifier of maven artifact
    @JsonProperty("classifier")
    private String classifier;

    // string, mandatory - extension of maven artifact
    @JsonProperty("mvn_type")
    private String mvnType;

    // string, mandatory
    @JsonProperty("mvn_repository")
    private String mvnRepository;

    // object, mandatory
    @JsonProperty("hashes")
    private Hashes hashes;

    public MVN() {

    }

    public MVN(String groupId, String artifactId, String version,
               String serviceName, String classifier, String mvnType,
               String mvnRepository, Hashes hashes) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.serviceName = serviceName;
        this.classifier = classifier;
        this.mvnType = mvnType;
        this.mvnRepository = mvnRepository;
        this.hashes = hashes;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public String getMvnType() {
        return mvnType;
    }

    public void setMvnType(String mvnType) {
        this.mvnType = mvnType;
    }

    public String getMvnRepository() {
        return mvnRepository;
    }

    public void setMvnRepository(String mvnRepository) {
        this.mvnRepository = mvnRepository;
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
                "\"groupId\": " + "\"" + groupId + "\"" +
                ", \"artifactId\": " + "\"" + artifactId + "\"" +
                ", \"version\": " + "\"" + version + "\"" +
                ", \"serviceName\": " + "\"" + serviceName + "\"" +
                ", \"classifier\": " + "\"" + classifier + "\"" +
                ", \"mvnType\": " + "\"" + mvnType + "\"" +
                ", \"mvnRepository\": " + "\"" + mvnRepository + "\"" +
                ", \"hashes\": " + hashes +
                "}";
    }

    @Override
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("groupId", groupId == null);
        checkMap.put("artifactId", artifactId == null);
        checkMap.put("version", version == null);
        checkMap.put("mvn_type", mvnType == null);
        checkMap.put("mvn_repository", mvnRepository == null);
        checkMap.put("hashes", hashes == null);
        return checkMap;
    }
}
