package com.github.fabriciolfj.entrypoint.converte;

import com.github.fabriciolfj.entities.Financial;
import com.github.fabriciolfj.entrypoint.dto.response.FinancialResponse;

public class FinancialDTOConverter {

    private FinancialDTOConverter() { }

    public static FinancialResponse toResponse(final Financial financial) {
        return FinancialResponse.builder()
                .firstSalary(financial.firstSalary())
                .installment(financial.installment())
                .loan(financial.value())
                .portion(financial.portion())
                .build();
    }
}
