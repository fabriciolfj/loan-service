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

    public Financial getFinancial() {
        return financial;
    }

    public Integer getYearsBirthDate(final Integer years) {
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

    public BigDecimal getInstallment() {
        var result = getValueLoan().multiply(customer.getRate());
        var total = getValueLoan().add(result);
        return total.divide(BigDecimal.valueOf(getMonths()), 4, RoundingMode.HALF_DOWN);
    }

    public BigDecimal getValueLoan() {
        return customer.getSuggestedValue()
                .multiply(BigDecimal.valueOf(getMonths()))
                .setScale(4, RoundingMode.HALF_DOWN);
    }

    public Contract setFinancial(final Financial financial) {
        this.financial = financial;
        return this;
    }

    public Integer getMonths() {
        return this.deadline.months();
    }
}
