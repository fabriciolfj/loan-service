package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.busines.ProviderListContract;
import com.github.fabriciolfj.entities.Contract;
import io.smallrye.mutiny.Multi;
import lombok.RequiredArgsConstructor;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class ListAllContractsUseCase {

    private final ProviderListContract providerListContract;

    public Multi<Contract> execute(final int pageInit) {
        return providerListContract.process(pageInit);
    }
}
