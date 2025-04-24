package org.rtm.web.handler;

import org.rtm.exception.DuplicatePersonalNumberException;
import org.rtm.model.dto.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatePersonalNumberException.class)
    public ResponseEntity<ApiError> handleDuplicatePersonalNumberException(DuplicatePersonalNumberException ex) {
            ApiError body = new ApiError("PERSONAL_NUMBER_ALREADY_EXISTS", ex.getMessage()); //TODO make Enums for the exceptions
            return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
