package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.IntegerWrapper;
import com.netcracker.mergemodels.wrapper.Type;

public class DescriptionMerge {
    // "version" field
    // integer, mandatory
    private IntegerWrapper version;

    private Type operationType = Type.NONE;

    public IntegerWrapper getVersion() {
        return version;
    }

    public void setVersion(IntegerWrapper version) {
        this.version = version;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
