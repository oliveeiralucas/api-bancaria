package com.oliveeiralucas.apibancaria.controller;

import com.oliveeiralucas.apibancaria.entity.Wallet;
import com.oliveeiralucas.apibancaria.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    //lista todas as carteiras
    @GetMapping
    public List<Wallet> list(){
        return walletService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> listById(
            @PathVariable Long id){
        Wallet wallet = walletService.findUserById(id);
        if (wallet != null){
            return ResponseEntity.ok(wallet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para consultar saldo
    @GetMapping("/{id}/balance")
    public ResponseEntity<String> getBalance(@PathVariable Long id) {
        BigDecimal balance = walletService.getBalance(id);
        return ResponseEntity.ok("Balance: " + balance.toString());
    }

    @PostMapping
    public Wallet createWallet(@RequestBody Wallet wallet){
        return walletService.create(wallet);
    }

    // Endpoint para realizar saque
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<String> withdraw(
            @PathVariable Long id,
            @RequestParam BigDecimal amount) {
        Wallet wallet = walletService.withDraw(id, amount);
        return ResponseEntity.ok("Withdrawal successful. New balance: " + wallet.getBalance());
    }

    // Endpoint para realizar depósito
    @PutMapping("/{id}/deposit")
    public ResponseEntity<String> deposit(
            @PathVariable Long id,
            @RequestParam BigDecimal amount) {
        Wallet wallet = walletService.deposit(id, amount);
        return ResponseEntity.ok("Deposit successful. New balance: " + wallet.getBalance());
    }

    // Endpoint para alterar nome
    @PutMapping("/{id}/name")
    public ResponseEntity<String> changeName(
            @PathVariable Long id,
            @RequestParam String newName) {
        Wallet wallet = walletService.changeName(id, newName);
        return ResponseEntity.ok("Name changed successfully. New name: " + wallet.getFullName());
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<String> changeEmail(
            @PathVariable Long id,
            @RequestParam String newEmail){
        Wallet wallet = walletService.changeEmail(id, newEmail);
        return ResponseEntity.ok("Email changed sucessfuly. New email: " + wallet.getEmail());
    }

    // Endpoint para alterar senha
    @PutMapping("/{id}/password")
    public ResponseEntity<String> changePassword(
            @PathVariable Long id,
            @RequestParam String newPassword) {
        Wallet wallet = walletService.changePassword(id, newPassword);
        return ResponseEntity.ok("Password changed successfully.");
    }

    @PutMapping("/{id}/type")
    public ResponseEntity<String> changeType(
            @PathVariable Long id,
            @RequestParam int newType){
        Wallet wallet = walletService.changeType(id, newType);
        return ResponseEntity.ok("Type changed successfully: New Type: " + wallet.getType());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id){
        Wallet wallet = walletService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
