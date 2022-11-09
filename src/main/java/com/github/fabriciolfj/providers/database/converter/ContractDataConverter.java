package com.github.fabriciolfj.providers.database.converter;

import com.github.fabriciolfj.entities.*;
import com.github.fabriciolfj.providers.database.data.ContractData;
import com.github.fabriciolfj.providers.database.data.CustomerData;

import java.math.BigDecimal;
import java.util.List;

public class ContractDataConverter {

    private ContractDataConverter() { }

    public static ContractData toData(final Contract contract) {
        var customer = CustomerData.builder()
                .salary(contract.getSalary())
                .birthDate(contract.getBirthDate())
                .document(contract.getDocument())
                .score(contract.getScore())
                .build();

        var contractData= ContractData.builder()
                .loan(contract.getLoan())
                .code(contract.getCode())
                .modality(contract.getModality())
                .dateCreation(contract.getDateCreation())
                .portion(contract.getPortion())
                .fees(BigDecimal.ZERO)
                .rate(BigDecimal.ZERO)
                .customer(customer)
                .build();

        customer.setContracts(List.of(contractData));
        return contractData;
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
