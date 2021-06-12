package com.netcracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.zip.CheckedInputStream;

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
    LinkedList<String> validate() {
        LinkedList<String> checkList = new LinkedList<>();


        return checkList;
    }
}
