package br.com.simplified_picpay.simplified_picpay.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(String message, LocalDateTime timestamp) {
}
