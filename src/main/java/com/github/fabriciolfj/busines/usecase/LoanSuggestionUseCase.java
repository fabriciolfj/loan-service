package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.busines.ProviderSaveContract;
import com.github.fabriciolfj.entities.Contract;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class LoanSuggestionUseCase {

    private final CalculateAgeRiskUseCase calculateAgeRiskUseCase;
    private final CalculateFinanceUseCase calculateFinanceUseCase;
    private final ProviderSaveContract providerSaveContract;

    public Uni<Contract> execute(final Contract contract) {
        return Uni.createFrom().item(contract)
                .onItem()
                .transformToUni(calculateAgeRiskUseCase::execute)
                .invoke(c -> log.info("Contract risk age executed {}", c.getDeadline()))
                .onItem()
                .transformToUni(calculateFinanceUseCase::execute)
                .onItem()
                .transformToUni(providerSaveContract::process)
                .invoke(c -> log.info("Contract finance executed {}", c.getFinancial()));
    }
}
