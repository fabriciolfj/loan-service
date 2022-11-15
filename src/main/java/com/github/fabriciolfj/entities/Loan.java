package com.github.fabriciolfj.entities;

import java.math.BigDecimal;
import java.time.LocalDate;


public record Loan(Integer portion, BigDecimal installment, BigDecimal loan, BigDecimal totalLoan) {

}
