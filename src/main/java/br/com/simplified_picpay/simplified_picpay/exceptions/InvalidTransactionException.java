package br.com.simplified_picpay.simplified_picpay.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTransactionException extends BusinessException {
    public InvalidTransactionException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
