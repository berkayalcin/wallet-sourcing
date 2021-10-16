package com.example.walletservice.event.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletCreatedEvent {
    private String id;
    private String userId;
    private BigDecimal balance;
}
