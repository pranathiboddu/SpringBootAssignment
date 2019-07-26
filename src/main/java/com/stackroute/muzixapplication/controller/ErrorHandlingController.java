package com.stackroute.muzixapplication.controller;

import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {
    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<Object> exception1(TrackAlreadyExistsException exception) {
        return new ResponseEntity<>("Track already Exists", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<Object> exception2(TrackNotFoundException exception) {
        return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);


    }
}