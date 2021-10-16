package com.example.walletservice.command.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DepositRequest {
    private String id;
    private BigDecimal amount;
}
