package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

// array, mandatory - list of direct urls. always contains only one element
@JsonIgnoreProperties(ignoreUnknown = true)
public class File extends AbstractModel {
    // string, mandatory - direct url of artifact
    @JsonProperty("file")
    private String file;

    public File() {

    }

    public File(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "\"" + file + "\"";
    }

    @Override
    public boolean equals(Object model) {
        if (this == model)
            return true;

        if (model == null)
            return false;

        if (this.getClass() != model.getClass())
            return false;

        File file = (File) model;

        if (this.file != null)
            return this.file.equals(file.getFile());

        return false;
    }

    @Override
    public HashMap<String,Boolean> validate() {
        HashMap<String,Boolean> checkMap = new HashMap<>();
        checkMap.put("file", file == null);
        return checkMap;
    }
}
