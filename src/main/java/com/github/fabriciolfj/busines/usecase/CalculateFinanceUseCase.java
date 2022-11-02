package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.entities.Financial;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;

@ApplicationScoped
public class CalculateFinanceUseCase {

    private static final Long DAY = 30L;

    public Uni<Contract> execute(final Contract contract) {
        return Uni.createFrom()
                .item(contract)
                .onItem()
                .transform(c -> new Financial(c.getMonths(), c.getValueLoan(), LocalDate.now().plusDays(DAY)))
                .onItem()
                .transform(financial -> contract.setFinancial(financial));
    }
}
