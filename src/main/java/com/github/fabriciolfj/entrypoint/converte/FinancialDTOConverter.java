package com.github.fabriciolfj.entrypoint.converte;

import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.entrypoint.dto.response.FinancialResponse;

public class FinancialDTOConverter {

    private FinancialDTOConverter() { }

    public static FinancialResponse toResponse(final Contract contract) {
        var loan = contract.getLoan();
        var deadLine = contract.getDeadline();
        return FinancialResponse.builder()
                .firstSalary(deadLine.getFirstSalary())
                .installment(loan.installment())
                .loan(loan.loan())
                .portion(loan.portion())
                .code(contract.getCode())
                .status(contract.getStatus().getDescribe())
                .build();
    }
}
