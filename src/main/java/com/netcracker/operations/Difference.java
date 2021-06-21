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
    private final LinkedList<String> paths;
    private final AbstractModel firstModel;
    private final AbstractModel secondModel;

    public Difference(AbstractModel firstModel, AbstractModel secondModel) {
        this.firstModel = firstModel;
        this.secondModel = secondModel;
        operations = new LinkedList<>();
        paths = new LinkedList<>();
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

    public void addPath(String path) {
        paths.addLast(path);
    }

    public LinkedList<Operations> getOperations() {
        return operations;
    }

    public LinkedList<String> getPaths() {
        return paths;
    }

    @Override
    public String toString() {
        return "Difference{" +
                "operations=" + operations +
                ", paths=" + paths +
                ", firstModel=" + firstModel +
                ", secondModel=" + secondModel +
                '}';
    }
}
