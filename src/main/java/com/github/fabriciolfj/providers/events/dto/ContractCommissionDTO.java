package com.github.fabriciolfj.providers.events.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractCommissionDTO {

    private String partner;
    private Integer portion;
    private BigDecimal loan;
    private BigDecimal installment;
}
