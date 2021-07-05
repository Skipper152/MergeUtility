package com.netcracker.mergemodels2;

import com.netcracker.mergemodels2.wrapper.StringWrapper;
import com.netcracker.mergemodels2.wrapper.Type;

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
