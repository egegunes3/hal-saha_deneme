package com.egegunes.backend1.Common;


public class GeneralException extends RuntimeException {
    private final CommonHttpStatus status;

    public GeneralException(CommonHttpStatus status) {
        super(status.getDescription());
        this.status = status;
    }

    public CommonHttpStatus getStatus() {
        return status;
    }
}
