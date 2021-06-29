package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.IntegerWrapper;

public class DescriptionMerge {
    // "version" field
    // integer, mandatory
    private IntegerWrapper version;

    public IntegerWrapper getVersion() {
        return version;
    }

    public void setVersion(IntegerWrapper version) {
        this.version = version;
    }
}
