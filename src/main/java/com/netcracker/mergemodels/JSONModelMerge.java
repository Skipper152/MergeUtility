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

    public JSONModelMerge(MetadataMerge metadataMerge, ArrayList<ServiceMerge> serviceMerges,
                          ArrayList<ArtifactMerge> artifactMerges, ArrayList<ScriptMerge> scriptMerges,
                                        ArrayList<RPMMerge> rpmMerges, ParametersMerge parametersMerge) {
        this.metadataMerge = metadataMerge;
        this.serviceMerges = serviceMerges;
        this.artifactMerges = artifactMerges;
        this.scriptMerges = scriptMerges;
        this.rpmMerges = rpmMerges;
        this.parametersMerge = parametersMerge;
    }

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
}
