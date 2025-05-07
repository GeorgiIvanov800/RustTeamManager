package org.rtm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatePersonalNumberException extends RuntimeException {
    private final Object[] args;

    public DuplicatePersonalNumberException(String personalNumber) {
        super("error.user.duplicatePersonalNumber");
        this.args = new Object[]{personalNumber};
    }
}
