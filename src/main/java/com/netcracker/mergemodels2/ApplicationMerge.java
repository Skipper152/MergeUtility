package com.netcracker.mergemodels2;

import com.netcracker.mergemodels2.wrapper.StringWrapper;
import com.netcracker.mergemodels2.wrapper.Type;


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
