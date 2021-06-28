package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.IntegerWrapper;

public class DescriptionMerge {
    // "version" field
    // integer, mandatory
    private final IntegerWrapper version;

    public DescriptionMerge(IntegerWrapper version) {
        this.version = version;
    }

    public IntegerWrapper getVersion() {
        return version;
    }
}
