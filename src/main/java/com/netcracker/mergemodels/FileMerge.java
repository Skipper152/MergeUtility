package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;
import com.netcracker.mergemodels.wrapper.Type;

public class FileMerge {
    // "file" field
    // string, mandatory - direct url of artifact
    private StringWrapper file;

    private Type operationType = Type.NONE;

    public StringWrapper getFile() {
        return file;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }

    public void setFile(StringWrapper file) {
        this.file = file;
    }
}
