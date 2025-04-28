package org.rtm.exception;

import lombok.Getter;

@Getter
public class DuplicateSleeveNumberException extends RuntimeException {

    private final Integer sleeveNumber;

    public DuplicateSleeveNumberException(Integer sleeveNumber) {

        super("Duplicate sleeve number: " + sleeveNumber);
        this.sleeveNumber = sleeveNumber;

    }

}
