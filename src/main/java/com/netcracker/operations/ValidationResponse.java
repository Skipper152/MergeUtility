package com.netcracker.operations;

import java.util.LinkedList;

public class ValidationResponse {
    private final LinkedList<String> firstLinkedListErrors;
    private final LinkedList<String> secondLinkedListErrors;

    public ValidationResponse(LinkedList<String> firstLinkedListErrors, LinkedList<String> secondLinkedListErrors) {
        this.firstLinkedListErrors = firstLinkedListErrors;
        this.secondLinkedListErrors = secondLinkedListErrors;
    }

    public LinkedList<String> getFirstLinkedListErrors() {
        return firstLinkedListErrors;
    }

    public LinkedList<String> getSecondLinkedListErrors() {
        return secondLinkedListErrors;
    }
}
