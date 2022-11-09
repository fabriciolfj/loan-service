package com.github.fabriciolfj.providers.events.converter;

import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.providers.events.dto.ContractCommissionDTO;

public class ContractCommissionDTOConverter {

    private ContractCommissionDTOConverter() { }

    public static ContractCommissionDTO toDTO(final Contract contract) {
        return ContractCommissionDTO.builder()
                .installment(contract.getInstallment())
                .loan(contract.getLoanValue())
                .portion(contract.getPortion())
                .build();
    }
}
