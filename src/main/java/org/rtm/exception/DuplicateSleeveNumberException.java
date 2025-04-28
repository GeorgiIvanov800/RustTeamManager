package org.rtm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateSleeveNumberException extends RuntimeException {
   private final Object[] args;

    public DuplicateSleeveNumberException(Integer sleeveNumber) {

        super("error.sleeve.duplicateSleeveNumber");
        this.args = new Object[]{sleeveNumber};

    }

}
