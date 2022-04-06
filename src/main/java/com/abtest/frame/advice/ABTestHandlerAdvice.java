package com.abtest.frame.advice;

import com.abtest.frame.exception.VariantPercentageOutOfBoundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ABTestHandlerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(VariantPercentageOutOfBoundsException.class)
    public ResponseEntity<String> handleError(VariantPercentageOutOfBoundsException ex, WebRequest request){
        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
