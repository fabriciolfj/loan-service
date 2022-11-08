package com.github.fabriciolfj.providers.database.converter;

import com.github.fabriciolfj.entities.*;
import com.github.fabriciolfj.providers.database.data.ContractData;

import java.math.BigDecimal;

public class ContractDataConverter {

    private ContractDataConverter() { }

    public static ContractData toData(final Contract contract) {
        return ContractData.builder()
                .loan(contract.getLoan())
                .code(contract.getCode())
                .birthDate(contract.getBirthDate())
                .modality(contract.getModality())
                .salary(contract.getSalary())
                .dateCreation(contract.getDateCreation())
                .portion(contract.getPortion())
                .document(contract.getDocument())
                .score(contract.getScore())
                .fees(BigDecimal.ZERO)
                .rate(BigDecimal.ZERO)
                .build();
    }

    public static Contract toEntity(final ContractData data) {
        var financial = new Financial(data.getPortion(), data.getLoan(), data.getSalary(), Modality.toEnum(data.getModality()));
        var customer = new Customer(data.getDocument(), data.getScore(), data.getBirthDate());
        var risk = new Risk(BigDecimal.ZERO, BigDecimal.ZERO);

        return Contract.builder()
                .code(data.getCode())
                .dateCreation(data.getDateCreation())
                .customer(customer)
                .financial(financial)
                .risk(risk)
                .build();
    }
}
