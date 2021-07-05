package com.netcracker.mergemodels2;

import com.netcracker.mergemodels2.wrapper.StringWrapper;
import com.netcracker.mergemodels2.wrapper.Type;

public class HashesMerge {
    // "sha1" field
    // string, mandatory
    private StringWrapper sha1;

    // "sha256" field
    // string, mandatory
    private StringWrapper sha256;

    private Type operationType = Type.NONE;

    public HashesMerge() {

    }

    public HashesMerge(StringWrapper sha1, StringWrapper sha256) {
        this.sha1 = sha1;
        this.sha256 = sha256;
    }

    public StringWrapper getSha1() {
        return sha1;
    }

    public StringWrapper getSha256() {
        return sha256;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setSha1(StringWrapper sha1) {
        this.sha1 = sha1;
    }

    public void setSha256(StringWrapper sha256) {
        this.sha256 = sha256;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
