package org.rtm.web.handler;

import lombok.AllArgsConstructor;
import org.rtm.exception.DuplicatePersonalNumberException;
import org.rtm.exception.DuplicateSleeveNumberException;
import org.rtm.exception.NotFoundException;
import org.rtm.model.dto.error.ApiErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;


    @ExceptionHandler(DuplicatePersonalNumberException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicatePersonalNumberException(DuplicatePersonalNumberException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);

        ApiErrorResponse body = new ApiErrorResponse(
                "409",
                msg,
                Instant.now(),
                Map.of("personalNumber", ex.getArgs()[0])
        );
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateSleeveNumberException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateSleeveNumberException(DuplicateSleeveNumberException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);

        ApiErrorResponse body = new ApiErrorResponse(
                "409",
                msg,
                Instant.now(),
                Map.of("sleeveNumber", ex.getArgs()[0])
        );
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(NotFoundException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);

        ApiErrorResponse body = new ApiErrorResponse(
                "404",
                msg,
                Instant.now(),
                Map.of("sleeveNumber", ex.getArgs()[0])
        );

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
