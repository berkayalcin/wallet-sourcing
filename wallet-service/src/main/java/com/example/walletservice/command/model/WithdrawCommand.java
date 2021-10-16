package com.example.walletservice.command.model;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WithdrawCommand {
    @TargetAggregateIdentifier
    private String id;
    private BigDecimal amount;
}
