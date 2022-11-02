package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.configproperties.LoanSupportProperties;
import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.entities.Customer;
import com.github.fabriciolfj.entities.Deadline;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class CalculateAgeRiskUseCase {

    private static final Integer MONTHS = 12;
    private final LoanSupportProperties loanSupportProperties;

    public Uni<Contract> execute(final Contract contract) {
        return Uni.createFrom().item(contract)
                .onItem()
                .transform(c -> c.getYearsBirthDate(loanSupportProperties.years))
                .onItem()
                .transformToUni(value -> {
                    if (value > MONTHS) {
                        return Uni.createFrom().failure(new RuntimeException("Age invalid do customer "));
                    }

                    var deadLine = new Deadline(value * MONTHS);
                    return Uni.createFrom().item(contract.setDeadLine(deadLine));
                });
    }
}
