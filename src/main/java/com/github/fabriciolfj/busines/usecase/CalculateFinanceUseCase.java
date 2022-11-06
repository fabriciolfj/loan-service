package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.exceptions.LoanInvalidException;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculateFinanceUseCase {

    private static final Long DAY = 30L;

    public Uni<Contract> execute(final Contract contract) {
        return Uni.createFrom()
                .item(contract)
                .onItem()
                .transform(c ->  {
                    if (c.isValid()) {
                        return c;
                    }

                    throw new LoanInvalidException();
                }).onItem()
                .transform(c -> c.createLoan());
    }
}
