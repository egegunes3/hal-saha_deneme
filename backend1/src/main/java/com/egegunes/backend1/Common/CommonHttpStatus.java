package com.egegunes.backend1.Common;

import lombok.Data;


public enum CommonHttpStatus {
    // 2xx Success
    OK(200, "OK", "Request has succeeded."),

    // 4xx Client Errors
    BAD_REQUEST(400, "Bad Request", "The server could not understand the request due to invalid syntax."),
    UNAUTHORIZED(401, "Unauthorized", "The client must authenticate itself to get the requested response."),
    FORBIDDEN(403, "Forbidden", "The client does not have access rights to the content."),
    NOT_FOUND(404, "Not Found", "The server can not find the requested resource."),

    // 5xx Server Errors
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "The server has encountered a situation it doesn't know how to handle.");

    private final int code;
    private final String reasonPhrase;
    private final String description;

    CommonHttpStatus(int code, String reasonPhrase, String description) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public String getDescription() {
        return description;
    }
}

