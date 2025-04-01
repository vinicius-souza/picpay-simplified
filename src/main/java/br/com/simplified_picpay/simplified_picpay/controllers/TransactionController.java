package br.com.simplified_picpay.simplified_picpay.controllers;

import br.com.simplified_picpay.simplified_picpay.domain.transaction.Transaction;
import br.com.simplified_picpay.simplified_picpay.dtos.TransactionDTO;
import br.com.simplified_picpay.simplified_picpay.services.TransactionService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private Mapper mapper;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAll(){
        return transactionService.getAll();
    }

    @PostMapping
    public Transaction create(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO);
    }
}
