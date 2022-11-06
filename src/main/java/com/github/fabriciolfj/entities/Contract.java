package com.github.fabriciolfj.entities;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contract {

    private Financial financial;
    private Deadline deadline;
    private Customer customer;
    private Loan loan;
    private Risk risk;

    public Financial getFinancial() {
        return financial;
    }

    public Integer getYearsBirthDate(final Integer years) {
        return LocalDate.now().plusYears(years).getYear() - customer.getYearsBirthDate();
    }

    public Boolean isValid() {
        return financial.isLoanValid();
    }

    public Contract setDeadLine(final Deadline deadline) {
        this.deadline = deadline;
        return this;
    }

    public Contract init(final Customer customer, final Financial financial) {
        this.customer = customer;
        this.financial = financial;
        return this;
    }

    public Contract createLoan() {
        this.loan =  new Loan(financial.portion(), financial.calculateInstallment(), financial.loan(), deadline.getFirstSalary());
        return this;
    }
}
