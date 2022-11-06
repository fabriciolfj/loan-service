package com.github.fabriciolfj.entrypoint.converte;

import com.github.fabriciolfj.entities.Loan;
import com.github.fabriciolfj.entrypoint.dto.response.FinancialResponse;

public class FinancialDTOConverter {

    private FinancialDTOConverter() { }

    public static FinancialResponse toResponse(final Loan loan) {
        return FinancialResponse.builder()
                .firstSalary(loan.firstSalary())
                .installment(loan.installment())
                .loan(loan.loan())
                .portion(loan.portion())
                .build();
    }
}
