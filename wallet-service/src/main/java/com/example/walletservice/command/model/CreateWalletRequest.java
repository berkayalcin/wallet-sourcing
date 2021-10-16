package com.example.walletservice.command.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateWalletRequest {
    private String userId;
}
