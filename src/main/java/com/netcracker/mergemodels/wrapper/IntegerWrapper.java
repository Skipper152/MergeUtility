package com.netcracker.mergemodels.wrapper;

public class IntegerWrapper {
    private final Integer oldValue;
    private final Integer newValue;
    private final Type operationType;

    public IntegerWrapper(Integer oldValue, Integer newValue, Type operationType) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.operationType = operationType;
    }

    public Integer getOldValue() {
        return oldValue;
    }

    public Integer getNewValue() {
        return newValue;
    }

    public Type getOperationType() {
        return operationType;
    }
}
