package br.com.simplified_picpay.simplified_picpay.services;

import br.com.simplified_picpay.simplified_picpay.domain.transaction.Transaction;
import br.com.simplified_picpay.simplified_picpay.domain.wallet.Wallet;
import br.com.simplified_picpay.simplified_picpay.domain.wallet.WalletType;
import br.com.simplified_picpay.simplified_picpay.dtos.TransactionDTO;
import br.com.simplified_picpay.simplified_picpay.exceptions.InvalidTransactionException;
import br.com.simplified_picpay.simplified_picpay.mapper.Mapper;
import br.com.simplified_picpay.simplified_picpay.repositories.TransactionRepository;
import br.com.simplified_picpay.simplified_picpay.repositories.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final Mapper mapper;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, Mapper mapper) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.mapper = mapper;
    }

    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }

    @Transactional
    public Transaction create(TransactionDTO transactionDTO) {
        this.validate(transactionDTO);

        var payerWallet = walletRepository.findById(transactionDTO.payer());
        var payeeWallet = walletRepository.findById(transactionDTO.payee());

        payerWallet.get().withdraw(transactionDTO.value());
        payeeWallet.get().deposit(transactionDTO.value());

        var newTransaction = transactionRepository.save(mapper.toTransaction(transactionDTO, payerWallet.get(), payeeWallet.get()));

        this.walletRepository.save(payerWallet.get());
        this.walletRepository.save(payeeWallet.get());

        return newTransaction;
    }

    public void validate(TransactionDTO transactionDTO) {
        var payerWallet = walletRepository.findById(transactionDTO.payer())
                .orElseThrow(() -> new InvalidTransactionException("Payer wallet not found"));
        var payeeWallet = walletRepository.findById(transactionDTO.payee())
                .orElseThrow(() -> new InvalidTransactionException("Payee wallet not found"));

        validateDifferentWallets(payerWallet, payeeWallet);
        validatePayerWalletType(payerWallet);
        validateSufficientBalance(transactionDTO.value(), payerWallet.getBalance());
    }

    private void validateDifferentWallets(Wallet payerWallet, Wallet payeeWallet) {
        if (payerWallet.equals(payeeWallet)) {
            throw new InvalidTransactionException("Payer and payee wallets must be different");
        }
    }

    private void validatePayerWalletType(Wallet payerWallet) {
        if (payerWallet.getType() == WalletType.LOJISTA) {
            throw new InvalidTransactionException("Payer wallet cannot be of type LOJISTA");
        }
    }

    private void validateSufficientBalance(BigDecimal amount, BigDecimal balance) {
        if (amount.compareTo(balance) > 0) {
            throw new InvalidTransactionException("Payer wallet does not have enough balance to cover the transaction amount");
        }
    }
}
