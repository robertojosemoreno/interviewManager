package org.example.interviewmanager.utils.exception;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.NoSuchAlgorithmException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<String> handleCompanyNotFoundException(CompanyNotFoundException e) {
        return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(OpenPositionNotFoundException.class)
    public ResponseEntity<String> handleOpenPositionNotFoundException(OpenPositionNotFoundException e) {
        return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<String> handleNoSuchAlgorithmException(NoSuchAlgorithmException e) {
        return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.EXPECTATION_FAILED );
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignatureException(SignatureException e) {
        return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.FORBIDDEN );
    }

}
