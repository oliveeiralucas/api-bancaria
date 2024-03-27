package com.oliveeiralucas.apibancaria.repository;

import com.oliveeiralucas.apibancaria.entity.Transaction;
import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}
