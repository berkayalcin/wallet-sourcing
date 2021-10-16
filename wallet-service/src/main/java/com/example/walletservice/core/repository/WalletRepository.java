package com.example.walletservice.core.repository;

import com.example.walletservice.core.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByUserId(final String userId);
    Wallet findWalletById(final String id);
}
