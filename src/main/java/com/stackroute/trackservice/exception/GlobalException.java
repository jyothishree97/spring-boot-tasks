package com.stackroute.trackservice.exception;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<?> trackNotFoundException(final TrackNotFoundException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrackAlreadyExistsException.class)
    public ResponseEntity<?> trackAlreadyExistsException(final TrackAlreadyExistsException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }

}
