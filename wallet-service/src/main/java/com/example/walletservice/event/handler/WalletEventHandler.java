package com.example.walletservice.event.handler;

import com.example.walletservice.core.entity.Wallet;
import com.example.walletservice.core.repository.WalletRepository;
import com.example.walletservice.event.model.AmountDepositedEvent;
import com.example.walletservice.event.model.AmountWithdrawnEvent;
import com.example.walletservice.event.model.WalletCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@ProcessingGroup("wallet-group")
public class WalletEventHandler {
    private final WalletRepository walletRepository;

    @EventHandler
    public void on(final WalletCreatedEvent walletCreatedEvent) {
        checkIfUserHasWallet(walletCreatedEvent.getUserId());
        final var wallet = Wallet.builder()
                .balance(new BigDecimal(0))
                .id(walletCreatedEvent.getId())
                .userId(walletCreatedEvent.getUserId())
                .build();
        walletRepository.save(wallet);
    }

    private void checkIfUserHasWallet(final String userId) {
        final var wallet = walletRepository.findByUserId(userId);
        if (wallet.isPresent()) {
            throw new IllegalStateException("User has already got wallet");
        }
    }

    @EventHandler
    public void on(final AmountDepositedEvent amountDepositedEvent) {
        final var wallet = walletRepository.findWalletById(amountDepositedEvent.getId());
        wallet.setBalance(wallet.getBalance().add(amountDepositedEvent.getDepositedAmount()));
        walletRepository.save(wallet);
    }

    @EventHandler
    public void on(final AmountWithdrawnEvent amountWithdrawnEvent) {
        final var wallet = walletRepository.findWalletById(amountWithdrawnEvent.getId());
        wallet.setBalance(wallet.getBalance().subtract(amountWithdrawnEvent.getWithdrawnAmount()));
        walletRepository.save(wallet);
    }
}
