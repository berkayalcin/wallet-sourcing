package com.example.walletservice.command.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WithdrawRequest {
    private String id;
    private BigDecimal amount;
}
