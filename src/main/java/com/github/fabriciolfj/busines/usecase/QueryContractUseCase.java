package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.busines.ProviderFindContract;
import com.github.fabriciolfj.entities.Contract;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class QueryContractUseCase {

    private final ProviderFindContract providerFindContract;

    public Uni<Contract> execute(final String code) {
        return providerFindContract.process(code);
    }
}
