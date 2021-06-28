package com.netcracker.mergemodels.wrapper;

public class BooleanWrapper {
    private final Boolean oldValue;
    private final Boolean newValue;
    private final Type operationType;

    public BooleanWrapper(Boolean oldValue, Boolean newValue, Type operationType) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.operationType = operationType;
    }

    public Boolean getOldValue() {
        return oldValue;
    }

    public Boolean getNewValue() {
        return newValue;
    }

    public Type getOperationType() {
        return operationType;
    }
}
