package com.github.fabriciolfj.entrypoint.converte;

import com.github.fabriciolfj.entities.*;
import com.github.fabriciolfj.entrypoint.dto.request.CustomerRequest;

import java.time.LocalDate;

public class ContractDTOConverter {

    private ContractDTOConverter() { }

    public static Contract toEntity(final CustomerRequest request) {
        var customer =  new Customer(request.getDocument(),
                request.getScore(),
                LocalDate.parse(request.getBirthDate()));

        var financial = new Financial(request.getPortion(), request.getValue(), request.getSalary(), Modality.toEnum(request.getModality()));

        var contract = new Contract();
        return contract.init(customer, financial, request.getPartner());
    }
}
