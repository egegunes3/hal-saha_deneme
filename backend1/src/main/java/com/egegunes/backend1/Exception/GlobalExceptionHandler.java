package com.egegunes.backend1.Exception;

import com.egegunes.backend1.Common.CommonHttpStatus;
import com.egegunes.backend1.Common.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(GeneralException ex) {
        CommonHttpStatus status = ex.getStatus();
        return new ResponseEntity<>(new ErrorMessage(status.getDescription()), HttpStatus.valueOf(status.getCode()));
    }

    // Other exception handlers can be added here
}

class ErrorMessage {
    private String errorMessage;

    public ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
