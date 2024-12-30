package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.configproperties.LoanSupportProperties;
import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.entities.Deadline;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperties;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculateAgeRiskUseCase {

    @ConfigProperties
    private LoanSupportProperties loanSupportProperties;

    public Uni<Contract> execute(final Contract contract) {
        return Uni.createFrom().item(contract)
                .onItem()
                .transform(Contract::getYearsBirthDate)
                .onItem()
                .transformToUni(value -> {
                    if (value > loanSupportProperties.age) {
                        return Uni.createFrom().failure(new RuntimeException("Age invalid do customer "));
                    }

                    var deadLine = new Deadline();
                    return Uni.createFrom().item(contract.setDeadLine(deadLine));
                });
    }
}
