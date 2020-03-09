package com.gavaskar.app.ws.ui.model.response;

public enum RequestOperationStatus {
    SUCCESS ("Success"),
    DELETE ("Delete");

    private String status;

    RequestOperationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
