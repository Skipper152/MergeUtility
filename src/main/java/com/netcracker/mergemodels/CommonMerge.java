package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.StringWrapper;

public class CommonMerge {
    // "some-param"
    // string, mandatory - parameter value
    private final StringWrapper someParam;

    // "some-other-param"
    private final StringWrapper someOtherParam;

    // "some-else-param"
    private final StringWrapper someElseParam;

    public CommonMerge(StringWrapper someParam, StringWrapper someOtherParam, StringWrapper someElseParam) {
        this.someParam = someParam;
        this.someOtherParam = someOtherParam;
        this.someElseParam = someElseParam;
    }

    public StringWrapper getSomeParam() {
        return someParam;
    }

    public StringWrapper getSomeOtherParam() {
        return someOtherParam;
    }

    public StringWrapper getSomeElseParam() {
        return someElseParam;
    }
}
