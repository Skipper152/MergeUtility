package com.netcracker.operations;

import com.netcracker.models.AbstractModel;

import java.util.LinkedList;

enum Operations{
    REPLACE,
    DELETE,
    ADD
        }

public class Difference {
    private final LinkedList<Operations> operations;
    private final LinkedList<String> pathsFirstModel;
    private final LinkedList<String> pathsSecondModel;
    private final AbstractModel firstModel;
    private final AbstractModel secondModel;

    public Difference(AbstractModel firstModel, AbstractModel secondModel) {
        this.firstModel = firstModel;
        this.secondModel = secondModel;
        operations = new LinkedList<>();
        pathsFirstModel = new LinkedList<>();
        pathsSecondModel = new LinkedList<>();
    }

    public AbstractModel getFirstModel() {
        return firstModel;
    }

    public AbstractModel getSecondModel() {
        return secondModel;
    }

    public void addOperation(Operations operation) {
        operations.addLast(operation);
    }

    public void addPathFirstModel(String path) {
        pathsFirstModel.addLast(path);
    }

    public void addPathSecondModel(String path) {
        pathsSecondModel.addLast(path);
    }

    public LinkedList<Operations> getOperations() {
        return operations;
    }

    public LinkedList<String> getPathsFirstModel() {
        return pathsFirstModel;
    }

    public LinkedList<String> getPathsSecondModel() {
        return pathsSecondModel;
    }

    @Override
    public String toString() {
        return "Difference{" +
                "operations=" + operations +
                ", pathsFirstModel=" + pathsFirstModel +
                ", pathsSecondModel=" + pathsSecondModel +
                ", firstModel=" + firstModel +
                ", secondModel=" + secondModel +
                '}';
    }
}
