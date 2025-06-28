package com.assessment.contactmanager.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	 @ExceptionHandler(ContactNotFoundException.class)
	    public ResponseEntity<Map<String, String>> handleContactNotFound(ContactNotFoundException ex) {
	        Map<String, String> error = new HashMap<>();
	        error.put("error", ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	

}
