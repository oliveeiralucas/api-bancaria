package com.oliveeiralucas.apibancaria.repository;

import com.oliveeiralucas.apibancaria.entity.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
}
