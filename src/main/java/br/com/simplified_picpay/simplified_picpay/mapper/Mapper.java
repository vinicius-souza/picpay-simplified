package br.com.simplified_picpay.simplified_picpay.mapper;

import br.com.simplified_picpay.simplified_picpay.domain.transaction.Transaction;
import br.com.simplified_picpay.simplified_picpay.domain.wallet.Wallet;
import br.com.simplified_picpay.simplified_picpay.dtos.TransactionDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Transaction toTransaction(TransactionDTO transactionDTO, Wallet payer, Wallet payee) {
        return new Transaction(
                null,
                payer,
                payee,
                transactionDTO.value(),
                null
        );
    }
}
