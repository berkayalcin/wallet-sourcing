package com.example.walletservice.command.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletCreateResponse {
    private String id;
    private String userId;
    private BigDecimal balance;
}
