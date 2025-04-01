package br.com.simplified_picpay.simplified_picpay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long payer, Long payee){}