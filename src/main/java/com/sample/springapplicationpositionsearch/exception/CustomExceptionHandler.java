package com.sample.springapplicationpositionsearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CustomError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new CustomError(errorMap));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CustomError> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errorMap = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString().split("\\.")[1];
            String message = violation.getMessage();
            errorMap.put(field, message);
        }
        return ResponseEntity.badRequest().body(new CustomError(errorMap));
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CustomError> handleCustomException(CustomException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ex.getField().getValue(), ex.getMessage());
        return ResponseEntity.badRequest().body(new CustomError(errorMap));
    }
}
