package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;
import com.netcracker.mergemodels.wrapper.Type;

public class CommonMerge {
    // "some-param"
    // string, mandatory - parameter value
    private StringWrapper someParam;

    // "some-other-param"
    private StringWrapper someOtherParam;

    // "some-else-param"
    private StringWrapper someElseParam;

    private Type operationType = Type.NONE;

    public StringWrapper getSomeParam() {
        return someParam;
    }

    public StringWrapper getSomeOtherParam() {
        return someOtherParam;
    }

    public StringWrapper getSomeElseParam() {
        return someElseParam;
    }

    public void setSomeParam(StringWrapper someParam) {
        this.someParam = someParam;
    }

    public void setSomeOtherParam(StringWrapper someOtherParam) {
        this.someOtherParam = someOtherParam;
    }

    public void setSomeElseParam(StringWrapper someElseParam) {
        this.someElseParam = someElseParam;
    }

    public Type getOperationType() {
        return operationType;
    }

    public void setOperationType(Type operationType) {
        this.operationType = operationType;
    }
}
