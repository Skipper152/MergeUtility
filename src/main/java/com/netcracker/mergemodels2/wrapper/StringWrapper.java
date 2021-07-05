package com.netcracker.mergemodels2.wrapper;

public class StringWrapper {
    private final String oldValue;
    private final String newValue;
    private final Type operationType;

    public StringWrapper(String oldValue, String newValue, Type operationType) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.operationType = operationType;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public Type getOperationType() {
        return operationType;
    }
}
