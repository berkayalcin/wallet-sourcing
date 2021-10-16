package com.example.walletservice.query.handler;

import com.example.walletservice.core.repository.WalletRepository;
import com.example.walletservice.query.model.FindWalletQuery;
import com.example.walletservice.query.model.WalletDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletQueryHandler {
    private final WalletRepository walletRepository;

    @QueryHandler
    public WalletDTO on(final FindWalletQuery findWalletQuery) {
        final var wallet = walletRepository.findWalletById(findWalletQuery.getId());
        return WalletDTO.builder()
                .balance(wallet.getBalance())
                .id(wallet.getId())
                .userId(wallet.getUserId())
                .build();
    }
}
