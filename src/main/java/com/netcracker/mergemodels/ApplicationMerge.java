package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;
import com.netcracker.mergemodels.wrapper.Type;


public class ApplicationMerge {
    // "name" field
    // string, mandatory
    private StringWrapper name;
    private Type operationType = Type.NONE;

    public StringWrapper getName() {
        return name;
    }

    public void setName(StringWrapper name) {
        this.name = name;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
