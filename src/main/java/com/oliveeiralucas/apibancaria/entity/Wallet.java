package com.oliveeiralucas.apibancaria.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("WALLETS")
public class Wallet { // Classe Wallet como record para imutabilidade
    @Id
    Long id;
    String fullName; //nome completo do titular
    String cpf; //cpf do titular
    String email; //email do titular
    String password; //senha da carteira
    int type; //tipo de carteira (mapeado no enum)
    BigDecimal balance; //saldo da carteira

    public Wallet(Long id, String fullName, String cpf, String email, String password, int type, BigDecimal balance) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.type = type;
        this.balance = balance.setScale(2);
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getType() {
        return type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2);
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email){ this.email = email;}
    public void setType(int type) { this.type = type;}

    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.subtract(value));
    }

    public Wallet credit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.add(value));
    }

}
