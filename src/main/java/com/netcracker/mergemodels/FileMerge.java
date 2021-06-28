package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;

public class FileMerge {
    // "file" field
    // string, mandatory - direct url of artifact
    private final StringWrapper file;

    public FileMerge(StringWrapper file) {
        this.file = file;
    }

    public StringWrapper getFile() {
        return file;
    }
}
