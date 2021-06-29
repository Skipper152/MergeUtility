package com.netcracker.mergemodels;


import java.util.ArrayList;

public class JSONModelMerge {
    // "metadata" field
    // object, mandatory
    private MetadataMerge metadataMerge;

    // "services" field
    // array, mandatory, can be empty
    private ArrayList<ServiceMerge> serviceMerges;

    //"artifacts" field
    // array, mandatory, can be empty
    private ArrayList<ArtifactMerge> artifactMerges;

    // "script" field
    // array, mandatory, can be empty
    private ArrayList<ScriptMerge> scriptMerges;

    // "rpm" field
    // object, optional - describes rpm archive
    private ArrayList<RPMMerge> rpmMerges;

    // "parameters" field
    // object, mandatory, can be empty
    private ParametersMerge parametersMerge;

    public MetadataMerge getMetadataMerge() {
        return metadataMerge;
    }

    public ArrayList<ServiceMerge> getServiceMerges() {
        return serviceMerges;
    }

    public ArrayList<ArtifactMerge> getArtifactMerges() {
        return artifactMerges;
    }

    public ArrayList<ScriptMerge> getScriptMerges() {
        return scriptMerges;
    }

    public ArrayList<RPMMerge> getRpmMerges() {
        return rpmMerges;
    }

    public ParametersMerge getParametersMerge() {
        return parametersMerge;
    }

    public void setMetadataMerge(MetadataMerge metadataMerge) {
        this.metadataMerge = metadataMerge;
    }

    public void setServiceMerges(ArrayList<ServiceMerge> serviceMerges) {
        this.serviceMerges = serviceMerges;
    }

    public void setArtifactMerges(ArrayList<ArtifactMerge> artifactMerges) {
        this.artifactMerges = artifactMerges;
    }

    public void setScriptMerges(ArrayList<ScriptMerge> scriptMerges) {
        this.scriptMerges = scriptMerges;
    }

    public void setRpmMerges(ArrayList<RPMMerge> rpmMerges) {
        this.rpmMerges = rpmMerges;
    }

    public void setParametersMerge(ParametersMerge parametersMerge) {
        this.parametersMerge = parametersMerge;
    }
}
