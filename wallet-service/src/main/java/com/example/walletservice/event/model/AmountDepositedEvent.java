package com.example.walletservice.event.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmountDepositedEvent {
    private String id;
    private BigDecimal depositedAmount;
}
