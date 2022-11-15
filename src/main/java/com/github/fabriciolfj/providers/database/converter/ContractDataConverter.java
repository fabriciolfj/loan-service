package com.github.fabriciolfj.providers.database.converter;

import com.github.fabriciolfj.entities.*;
import com.github.fabriciolfj.providers.database.data.ContractData;
import com.github.fabriciolfj.providers.database.data.CustomerData;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContractDataConverter {

    private ContractDataConverter() { }

    public static ContractData toData(final Contract contract) {
        var loan = contract.getLoan();
        var customer = CustomerData.builder()
                .salary(contract.getSalary())
                .birthDate(contract.getBirthDate().toString())
                .document(contract.getDocument())
                .score(contract.getScore())
                .build();

        var contractData= ContractData.builder()
                .loan(loan.loan())
                .installmentValue(loan.installment())
                .totalLoan(loan.totalLoan())
                .portion(loan.portion())
                .code(contract.getCode())
                .status(contract.getStatus().getDescribe())
                .modality(contract.getModality())
                .dateCreation(contract.getDateCreation())
                .fees(BigDecimal.ZERO)
                .rate(BigDecimal.ZERO)
                .dateExpiration(contract.getExpirationDate().toString())
                .dueDate(contract.getDueDate().toString())
                .customer(customer)
                .build();

        return contractData;
    }

    public static Contract toEntity(final ContractData data) {
        var financial = new Financial(data.getPortion(), data.getLoan(), data.getSalary(), Modality.toEnum(data.getModality()));
        var deadLine = new Deadline(LocalDate.parse(data.getDueDate()));
        var customer = new Customer(data.getDocument(), data.getScore(), data.getBirthDate());
        var risk = new Risk(BigDecimal.ZERO, BigDecimal.ZERO);
        var loan = new Loan(data.getPortion(), data.getInstallmentValue(), data.getLoan(), data.getTotalLoan());

        return Contract.builder()
                .code(data.getCode())
                .status(StatusContract.toEnum(data.getStatus()))
                .dateCreation(data.getDateCreation())
                .customer(customer)
                .financial(financial)
                .expirationDate(LocalDate.parse(data.getDateExpiration()))
                .risk(risk)
                .deadline(deadLine)
                .loan(loan)
                .build();
    }
}
