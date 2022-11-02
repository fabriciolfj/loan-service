package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.configproperties.LoanSupportProperties;
import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.entities.Customer;
import com.github.fabriciolfj.entities.Deadline;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperties;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculateAgeRiskUseCase {

    private static final Integer MONTHS = 12;

    @ConfigProperties
    private LoanSupportProperties loanSupportProperties;

    public Uni<Contract> execute(final Contract contract) {
        return Uni.createFrom().item(contract)
                .onItem()
                .transform(c -> c.getYearsBirthDate(loanSupportProperties.years))
                .onItem()
                .transformToUni(value -> {
                    if (value > loanSupportProperties.age) {
                        return Uni.createFrom().failure(new RuntimeException("Age invalid do customer "));
                    }

                    var deadLine = new Deadline(loanSupportProperties.years * MONTHS);
                    return Uni.createFrom().item(contract.setDeadLine(deadLine));
                });
    }
}
