package com.netcracker.mergemodels.wrapper;

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

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + oldValue.hashCode();
        result = 31 * result + newValue.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        StringWrapper str = (StringWrapper) obj;

        return oldValue.equals(str.getOldValue()) &&
                newValue.equals(str.getNewValue());
    }

}
