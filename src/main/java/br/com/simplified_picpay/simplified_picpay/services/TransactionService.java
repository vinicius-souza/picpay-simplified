package br.com.simplified_picpay.simplified_picpay.services;

import br.com.simplified_picpay.simplified_picpay.domain.transaction.Transaction;
import br.com.simplified_picpay.simplified_picpay.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }
}
