package com.netcracker.mergemodels2;

import com.netcracker.mergemodels2.wrapper.StringWrapper;
import com.netcracker.mergemodels2.wrapper.Type;

public class ScriptMerge {
    // "service-short-name" field
    // string, optional
    private StringWrapper serviceShortName;

    // "start-point" field
    // string, optional
    private StringWrapper startPoint;

    // "end-point" field
    // string, optional
    private StringWrapper endPoint;

    // "script_name" field
    // string, mandatory
    private StringWrapper scriptName;

    // "hashes" field
    // object, mandatory
    private HashesMerge hashesMerge;

    // "url" field
    // string, mandatory - direct url of archive with script
    private StringWrapper url;

    private Type operationType = Type.NONE;

    public StringWrapper getServiceShortName() {
        return serviceShortName;
    }

    public StringWrapper getStartPoint() {
        return startPoint;
    }

    public StringWrapper getEndPoint() {
        return endPoint;
    }

    public StringWrapper getScriptName() {
        return scriptName;
    }

    public HashesMerge getHashes() {
        return hashesMerge;
    }

    public StringWrapper getUrl() {
        return url;
    }

    public void setServiceShortName(StringWrapper serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public void setStartPoint(StringWrapper startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(StringWrapper endPoint) {
        this.endPoint = endPoint;
    }

    public void setScriptName(StringWrapper scriptName) {
        this.scriptName = scriptName;
    }

    public void setHashes(HashesMerge hashesMerge) {
        this.hashesMerge = hashesMerge;
    }

    public void setUrl(StringWrapper url) {
        this.url = url;
    }

    public HashesMerge getHashesMerge() {
        return hashesMerge;
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
