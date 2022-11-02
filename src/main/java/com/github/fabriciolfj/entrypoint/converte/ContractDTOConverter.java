package com.github.fabriciolfj.entrypoint.converte;

import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.entities.Customer;
import com.github.fabriciolfj.entities.Modality;
import com.github.fabriciolfj.entrypoint.dto.request.CustomerRequest;

import java.time.LocalDate;

public class ContractDTOConverter {

    private ContractDTOConverter() { }

    public static Contract toEntity(final CustomerRequest request) {
        var customer =  new Customer(request.getName(),
                request.getScore(),
                LocalDate.parse(request.getBirthDate()),
                request.getSalary(),
                Modality.toEnum(request.getModality()));

        var contract = new Contract();
        return contract.setCustomer(customer);
    }
}
