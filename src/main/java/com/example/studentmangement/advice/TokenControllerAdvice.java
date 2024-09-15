package com.example.studentmangement.advice;

import com.example.studentmangement.exception.CourseNotFoundException;
import com.example.studentmangement.exception.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = TokenExpiredException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity handleTokenRefreshException(TokenExpiredException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFound(CourseNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity handleNOSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
