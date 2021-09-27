package com.spaz.backend.controller;

import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spaz.backend.exception.ErrorCode;
import com.spaz.backend.servicedto.BaseWebResponse;

@RestControllerAdvice
public class ExceptionRestController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BaseWebResponse> handleEntityNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseWebResponse.error(ErrorCode.USUARIO_NO_EXISTE));
    }
    
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<BaseWebResponse> handleDuplicateKeyException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseWebResponse.error(ErrorCode.CLAVE_DUPLICADA));
    }
}