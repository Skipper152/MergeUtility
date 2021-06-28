package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;
import com.netcracker.models.Hashes;

public class ScriptMerge {
    // "service-short-name" field
    // string, optional
    private final StringWrapper serviceShortName;

    // "start-point" field
    // string, optional
    private final StringWrapper startPoint;

    // "end-point" field
    // string, optional
    private final StringWrapper endPoint;

    // "script_name" field
    // string, mandatory
    private final StringWrapper scriptName;

    // "hashes" field
    // object, mandatory
    private final Hashes hashes;

    // "url" field
    // string, mandatory - direct url of archive with script
    private final StringWrapper url;

    public ScriptMerge(StringWrapper serviceShortName, StringWrapper startPoint, StringWrapper endPoint,
                       StringWrapper scriptName, Hashes hashes, StringWrapper url) {
        this.serviceShortName = serviceShortName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.scriptName = scriptName;
        this.hashes = hashes;
        this.url = url;
    }

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

    public Hashes getHashes() {
        return hashes;
    }

    public StringWrapper getUrl() {
        return url;
    }
}
