package com.github.fabriciolfj.entrypoint.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialResponse {

    private String code;
    private Integer portion;
    private BigDecimal installment;
    private BigDecimal loan;
    @JsonProperty("first_salary")
    private LocalDate firstSalary;
    private String status;
}
