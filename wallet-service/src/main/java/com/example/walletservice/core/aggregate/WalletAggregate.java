package com.example.walletservice.core.aggregate;

import com.example.walletservice.command.model.CreateWalletCommand;
import com.example.walletservice.command.model.DepositCommand;
import com.example.walletservice.command.model.WithdrawCommand;
import com.example.walletservice.event.model.AmountDepositedEvent;
import com.example.walletservice.event.model.AmountWithdrawnEvent;
import com.example.walletservice.event.model.WalletCreatedEvent;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Slf4j
public class WalletAggregate {
    @AggregateIdentifier
    private String id;
    private String userId;
    private BigDecimal balance;

    @CommandHandler
    private WalletAggregate(final CreateWalletCommand createWalletCommand) {
        if (createWalletCommand.getUserId() == null || createWalletCommand.getUserId().isBlank()) {
            throw new IllegalArgumentException("User Id Cannot be null or empty");
        }

        final var walletCreatedEvent = WalletCreatedEvent.builder()
                .balance(new BigDecimal(0))
                .id(createWalletCommand.getId())
                .userId(createWalletCommand.getUserId())
                .build();
        AggregateLifecycle.apply(walletCreatedEvent);
    }

    @CommandHandler
    private void handle(final DepositCommand depositCommand) {
        final var amountDepositedEvent = AmountDepositedEvent.builder()
                .depositedAmount(depositCommand.getAmount())
                .id(depositCommand.getId())
                .build();
        AggregateLifecycle.apply(amountDepositedEvent);
    }

    @CommandHandler
    private void handle(final WithdrawCommand withdrawCommand) {
        if (balance.subtract(withdrawCommand.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient Balance");
        }

        final var amountWithdrawnEvent = AmountWithdrawnEvent.builder()
                .withdrawnAmount(withdrawCommand.getAmount())
                .id(withdrawCommand.getId())
                .build();
        AggregateLifecycle.apply(amountWithdrawnEvent);
    }

    @EventSourcingHandler
    public void on(final WalletCreatedEvent walletCreatedEvent) {
        log.info("Wallet Created {}", walletCreatedEvent.getId());
        this.balance = walletCreatedEvent.getBalance();
        this.id = walletCreatedEvent.getId();
        this.userId = walletCreatedEvent.getUserId();
    }

    @EventSourcingHandler
    public void on(final AmountDepositedEvent amountDepositedEvent) {
        log.info("Amount Deposited {} : Amount {}", amountDepositedEvent.getId(), amountDepositedEvent.getDepositedAmount());
        this.balance = this.balance.add(amountDepositedEvent.getDepositedAmount());
    }

    @EventSourcingHandler
    public void on(final AmountWithdrawnEvent amountWithdrawnEvent) {
        log.info("Amount Withdrawn {} : Amount {}", amountWithdrawnEvent.getId(), amountWithdrawnEvent.getWithdrawnAmount());
        this.balance = this.balance.subtract(amountWithdrawnEvent.getWithdrawnAmount());
    }
}
