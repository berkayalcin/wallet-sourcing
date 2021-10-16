package com.example.walletservice.query.rest;

import com.example.walletservice.query.model.FindWalletQuery;
import com.example.walletservice.query.model.WalletDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("{id}")
    public WalletDTO getById(@PathVariable("id") final String id) {
        final var findWalletQuery = FindWalletQuery.builder()
                .id(id)
                .build();
        return queryGateway.query(findWalletQuery, ResponseTypes.instanceOf(WalletDTO.class)).join();
    }
}
