package com.oliveeiralucas.apibancaria.repository;

import com.oliveeiralucas.apibancaria.entity.Wallet;
import org.springframework.data.repository.ListCrudRepository;

public interface WalletRepository extends ListCrudRepository<Wallet, Long> {
}
