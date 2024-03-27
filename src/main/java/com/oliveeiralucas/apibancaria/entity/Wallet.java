package com.oliveeiralucas.apibancaria.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("WALLETS")
public record Wallet( // Classe Wallet como record para imutabilidade
        @Id Long id,
        String fullName, //nome completo do titular
        String cpf, //cpf do titular
        String email, //email do titular
        String password, //senha da carteira
        int type, //tipo de carteira (mapeado no enum)
        BigDecimal balance //saldo da carteira
) {
    public Wallet {
        balance = balance.setScale(2); // Arredonda o saldo para 2 casas decimais
    }

    //como estou utilizando um record, preciso instanciar novamente o objeto, caso eu queira mudar um campo
    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.subtract(value));
    }

    public Wallet credit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.add(value));
    }
}
