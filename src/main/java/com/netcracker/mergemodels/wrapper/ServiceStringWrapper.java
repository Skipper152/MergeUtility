package com.netcracker.mergemodels.wrapper;

public class ServiceStringWrapper {
    private String oldKey;
    private String newKey;
    private String oldValue;
    private String newValue;
    private Type operationType;

    public String getOldKey() {
        return oldKey;
    }

    public String getNewKey() {
        return newKey;
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

    public void setOldKey(String oldKey) {
        this.oldKey = oldKey;
    }

    public void setNewKey(String newKey) {
        this.newKey = newKey;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
