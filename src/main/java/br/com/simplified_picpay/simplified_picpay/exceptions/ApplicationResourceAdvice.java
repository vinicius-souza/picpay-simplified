package br.com.simplified_picpay.simplified_picpay.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApplicationResourceAdvice {
     @ExceptionHandler(BusinessException.class)
     public ResponseEntity<ErrorResponse> handleBussinessException(BusinessException ex) {
         return ResponseEntity
                 .status(ex.getStatus())
                 .body(new ErrorResponse(
                         ex.getMessage(),
                         LocalDateTime.now()
                 ));
     }
}
