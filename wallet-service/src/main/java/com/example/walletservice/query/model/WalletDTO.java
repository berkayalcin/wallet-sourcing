package com.example.walletservice.query.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletDTO {
    private String id;
    private String userId;
    private BigDecimal balance;
}
