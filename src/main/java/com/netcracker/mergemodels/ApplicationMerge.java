package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;


public class ApplicationMerge {
    // "name" field
    // string, mandatory
    private final StringWrapper name;

    public ApplicationMerge(StringWrapper name) {
        this.name = name;
    }

    public StringWrapper getName() {
        return name;
    }
}
