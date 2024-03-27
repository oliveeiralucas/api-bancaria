package com.oliveeiralucas.apibancaria.service;

import com.oliveeiralucas.apibancaria.entity.Transaction;
import com.oliveeiralucas.apibancaria.entity.Wallet;
import com.oliveeiralucas.apibancaria.enums.WalletType;
import com.oliveeiralucas.apibancaria.exception.InvalidTransactionException;
import com.oliveeiralucas.apibancaria.repository.TransactionRepository;
import com.oliveeiralucas.apibancaria.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    // injeta dependencies TransactionRepository e WalletRepository
    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional // anotação para trabalhar com banco de dados, se todas forem bem sucedidadas registra, caso contrário rollback
    public Transaction create(Transaction transaction){
        //1 - validar transaction
        validate(transaction);

        //2 - criar transaction
        var newTransaction = transactionRepository.save(transaction);

        //3 - debitar carteira
        var walletPayer = walletRepository.findById(transaction.payer()).get(); // Obtém a carteira do pagador
        var walletPayee = walletRepository.findById(transaction.payee()).get(); // Obtém a carteira do destinatário

        walletRepository.save(walletPayer.debit(transaction.value())); // Debita o valor da transação da carteira
        walletRepository.save(walletPayee.credit(transaction.value())); // Credita na carteira do destinatário

        return newTransaction;
    }

    /* regras de validação
     * pagador tem a carteira do tipo comum
     * pagador tem saldo suficiente
     * pagador não pode ser ele mesmo
     */
    private void validate(Transaction transaction){
        // Verifica se o pagador tem a carteira do tipo comum, saldo suficiente e não é ele mesmo
        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                    .map(payer -> isTransactionValid(transaction, payer)
                    ? transaction : null)
                        .orElseThrow(() -> new InvalidTransactionException("Invalid transaction")))
                .orElseThrow(() -> new InvalidTransactionException("Invalid transaction"));
    }

    //método estático
    private static boolean isTransactionValid(Transaction transaction, Wallet payer) {
        return payer.type() == WalletType.COMUM.getWalletType()  // Verifica se o tipo de carteira do pagador é comum
                && payer.balance().compareTo(transaction.value()) >= 0 // Verifica se o saldo do pagador é suficiente
                && !payer.id().equals(transaction.payee()); // Verifica se o pagador não é ele mesmo
    }

    //retorna lista de transações
    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
