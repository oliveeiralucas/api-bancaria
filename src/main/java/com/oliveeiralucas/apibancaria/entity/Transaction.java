package com.oliveeiralucas.apibancaria.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("TRANSACTIONS")
public record Transaction( // Classe como record para imutabilidade
        @Id Long id,
        Long payer, //id do pagador da transação
        Long payee, //id do recebedor da transação
        BigDecimal value, //valor da transação
        @CreatedDate LocalDateTime createdAt
){
        public Transaction {
            //arredonda para 2 casas decimais
            value = value.setScale(2);
        }
}
