package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;


public class ApplicationMerge {
    // "name" field
    // string, mandatory
    private StringWrapper name;

    public StringWrapper getName() {
        return name;
    }

    public void setName(StringWrapper name) {
        this.name = name;
    }
}
