package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;
import com.netcracker.models.Hashes;

public class RPMMerge {
    // "url" field
    // string, mandatory - direct url of rpm archive
    private final StringWrapper url;

    // "rpm_repository_name" field
    // string, mandatory
    private final StringWrapper rpmRepositoryName;

    // "hashes" field
    // object, mandatory
    private final Hashes hashes;

    // "service-short-name" field
    // string optional
    private final StringWrapper serviceShortName;

    public RPMMerge(StringWrapper url, StringWrapper rpmRepositoryName, Hashes hashes, StringWrapper serviceShortName) {
        this.url = url;
        this.rpmRepositoryName = rpmRepositoryName;
        this.hashes = hashes;
        this.serviceShortName = serviceShortName;
    }

    public StringWrapper getUrl() {
        return url;
    }

    public StringWrapper getRpmRepositoryName() {
        return rpmRepositoryName;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public StringWrapper getServiceShortName() {
        return serviceShortName;
    }
}
