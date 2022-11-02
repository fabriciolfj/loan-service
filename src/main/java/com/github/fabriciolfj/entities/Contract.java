package com.github.fabriciolfj.entities;

import lombok.*;

import java.math.BigDecimal;
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

    public Integer getYearsBirthDate(Integer years) {
        return LocalDate.now().plusYears(years).getYear() - customer.getYearsBirthDate();
    }

    public Contract setDeadLine(final Deadline deadline) {
        this.deadline = deadline;
        return this;
    }

    public Contract setCustomer(final Customer customer) {
        this.customer = customer;
        return this;
    }

    public BigDecimal getValueLoan() {
        return customer.getSuggestedValue().multiply(BigDecimal.valueOf(getMonths()));
    }

    public Contract setFinancial(final Financial financial) {
        this.financial = financial;
        return this;
    }

    public Integer getMonths() {
        return this.deadline.months();
    }
}
