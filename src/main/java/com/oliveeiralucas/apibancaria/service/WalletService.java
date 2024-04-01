package com.oliveeiralucas.apibancaria.service;

import com.oliveeiralucas.apibancaria.entity.Wallet;
import com.oliveeiralucas.apibancaria.exception.InsufficientFundsException;
import com.oliveeiralucas.apibancaria.exception.WalletNotFoundException;
import com.oliveeiralucas.apibancaria.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public List<Wallet> list() {
        return walletRepository.findAll();
    }

    public Wallet create(Wallet wallet) {
        return walletRepository.save(wallet);
    }
    public Optional<Wallet> findById(Long id){
        return walletRepository.findById(id);
    }

    public BigDecimal getBalance(Long walletId){
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        if (walletOptional.isPresent()){
            Wallet wallet = walletOptional.get();
            return wallet.getBalance();
        } else {
          throw new WalletNotFoundException("Wallet not found");
        }
    }

    @Transactional
    public Wallet withDraw(Long walletId, BigDecimal amount){
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        if (walletOptional.isPresent()){
            Wallet wallet = walletOptional.get();
            BigDecimal newBalance = wallet.getBalance().subtract(amount);
            if (newBalance.compareTo(BigDecimal.ZERO) >= 0){
                wallet.setBalance(newBalance);
                return walletRepository.save(wallet);
            } else {
                throw new InsufficientFundsException("Insufficinet funds");
            }
        } else {
            throw new WalletNotFoundException("Wallet not found");
        }
    }

    @Transactional
    public Wallet deposit(Long walletId, BigDecimal amount) {
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        if (walletOptional.isPresent()) {
            Wallet wallet = walletOptional.get();
            BigDecimal newBalance = wallet.getBalance().add(amount);
            wallet.setBalance(newBalance);
            return walletRepository.save(wallet);
        } else {
            throw new WalletNotFoundException("Wallet not found");
        }
    }

    @Transactional
    public Wallet changeName(Long walletId, String newName) {
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        if (walletOptional.isPresent()) {
            Wallet wallet = walletOptional.get();
            wallet.setFullName(newName);
            return walletRepository.save(wallet);
        } else {
            throw new WalletNotFoundException("Wallet not found");
        }
    }

    @Transactional
    public Wallet changePassword(Long walletId, String newPassword) {
        Optional<Wallet> walletOptional = walletRepository.findById(walletId);
        if (walletOptional.isPresent()) {
            Wallet wallet = walletOptional.get();
            wallet.setPassword(newPassword);
            return walletRepository.save(wallet);
        } else {
            throw new WalletNotFoundException("Wallet not found");
        }
    }

}
