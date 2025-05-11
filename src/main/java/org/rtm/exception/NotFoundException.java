package org.rtm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private final Object[] args;

    public NotFoundException(Integer sleeveNumber) {
        super("error.sleeve.notFound");
        this.args = new Object[]{sleeveNumber};
    }
}
