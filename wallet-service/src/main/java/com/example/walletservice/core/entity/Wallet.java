package com.example.walletservice.core.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String userId;
    private BigDecimal balance;
}
