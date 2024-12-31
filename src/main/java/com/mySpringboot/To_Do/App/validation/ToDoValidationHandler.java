package com.mySpringboot.To_Do.App.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ToDoValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String ,String>> exceptionHandler(MethodArgumentNotValidException ex){
        Map<String , String> exception = new HashMap<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors){
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            exception.put(fieldName,errorMessage);
        }
        return new  ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
