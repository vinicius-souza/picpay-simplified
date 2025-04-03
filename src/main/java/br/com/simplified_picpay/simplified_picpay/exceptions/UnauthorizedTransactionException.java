package br.com.simplified_picpay.simplified_picpay.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedTransactionException extends BusinessException{
    public UnauthorizedTransactionException(String message, HttpStatus status) {
        super(message, status);
    }
}
