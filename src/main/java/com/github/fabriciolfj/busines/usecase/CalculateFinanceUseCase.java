package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.exceptions.LoanInvalidException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculateFinanceUseCase {

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
                .transform(Contract::createLoan);
    }
}
