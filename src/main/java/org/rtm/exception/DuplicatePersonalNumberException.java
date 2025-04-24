package org.rtm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatePersonalNumberException extends RuntimeException {
    public DuplicatePersonalNumberException(String message) {
        super(message);
    }
}
