package com.gavaskar.app.ws.ui.model.response;

public enum RequestOperationName {
    DELETE ("Delete");

    private String operation;

    RequestOperationName(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
