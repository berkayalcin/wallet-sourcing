package com.example.walletservice.command.model;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateWalletCommand {
    @TargetAggregateIdentifier
    private String id;
    private String userId;
}
