package com.example.walletservice.command.rest;

import com.example.walletservice.command.model.*;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletCommandController {
    private final CommandGateway commandGateway;

    @PostMapping
    public String create(@RequestBody final CreateWalletRequest createWalletRequest) {
        final var createWalletCommand = buildCreateWalletCommand(createWalletRequest);
        return commandGateway.sendAndWait(createWalletCommand);
    }

    private CreateWalletCommand buildCreateWalletCommand(final CreateWalletRequest createWalletRequest) {
        return CreateWalletCommand.builder()
                .id(UUID.randomUUID().toString())
                .userId(createWalletRequest.getUserId())
                .build();
    }

    @PutMapping("{id}/deposit")
    public String deposit(@PathVariable final String id, @RequestBody final DepositRequest depositRequest) {
        final var depositCommand = DepositCommand.builder()
                .amount(depositRequest.getAmount())
                .id(id)
                .build();
        return commandGateway.sendAndWait(depositCommand);
    }

    @PutMapping("{id}/withdraw")
    public String withdraw(@PathVariable final String id, @RequestBody final WithdrawRequest withdrawRequest) {
        final var withdrawCommand = WithdrawCommand.builder()
                .amount(withdrawRequest.getAmount())
                .id(id)
                .build();
        return commandGateway.sendAndWait(withdrawCommand);
    }
}
