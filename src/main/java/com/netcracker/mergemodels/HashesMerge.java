package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;

public class HashesMerge {
    // "sha1" field
    // string, mandatory
    private StringWrapper sha1;

    // "sha256" field
    // string, mandatory
    private StringWrapper sha256;

    public HashesMerge(StringWrapper sha1, StringWrapper sha256) {
        this.sha1 = sha1;
        this.sha256 = sha256;
    }

    public StringWrapper getSha1() {
        return sha1;
    }

    public StringWrapper getSha256() {
        return sha256;
    }
}
