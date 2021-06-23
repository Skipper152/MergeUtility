package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

// object, optional
@JsonIgnoreProperties(ignoreUnknown = true)
public class Common extends AbstractModel {
    // string, mandatory - parameter value
    @JsonProperty("some-param")
    private String someParam;

    @JsonProperty("some-other-param")
    private String someOtherParam;

    @JsonProperty("some-else-param")
    private String someElseParam;

    public Common() {

    }

    public Common(String someParam, String someOtherParam, String someElseParam) {
        this.someParam = someParam;
        this.someOtherParam = someOtherParam;
        this.someElseParam = someElseParam;
    }

    public String getSomeParam() {
        return someParam;
    }

    public void setSomeParam(String someParam) {
        this.someParam = someParam;
    }

    public String getSomeOtherParam() {
        return someOtherParam;
    }

    public void setSomeOtherParam(String someOtherParam) {
        this.someOtherParam = someOtherParam;
    }

    public String getSomeElseParam() {
        return someElseParam;
    }

    public void setSomeElseParam(String someElseParam) {
        this.someElseParam = someElseParam;
    }

    @Override
    public boolean equals(Object model) {

        if (this == model)
            return true;

        if (model == null)
            return false;

        if (this.getClass() != model.getClass())
            return false;

        Common common = (Common) model;

        return someParam.equals(common.getSomeParam());
    }

    @Override
    public String toString() {
        return "{" +
                "\"someParam:\" " + "\"" + someParam + "\"" +
                ", \"someOtherParam:\" " + "\"" + someOtherParam + "\"" +
                ", \"someElseParam:\" " + "\"" + someElseParam + "\"" +
                '}';
    }

    @Override
    public HashMap<String, Boolean> validate() {
        HashMap<String, Boolean> checkMap = new HashMap<>();
        checkMap.put("some_param",someParam == null);
        return checkMap;
    }
}
