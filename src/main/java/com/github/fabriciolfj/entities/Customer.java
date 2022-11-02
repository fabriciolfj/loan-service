package com.github.fabriciolfj.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Customer(String name, Integer score, LocalDate birthDate, BigDecimal salary, Modality modality) {

    private static final BigDecimal ONE_THIRD_SALARY = BigDecimal.valueOf(0.3);

    public BigDecimal getSuggestedValue() {
        return getSalary().multiply(modality.getRate());
    }

    public BigDecimal getSalary() {
        return salary.multiply(ONE_THIRD_SALARY);
    }

    public Integer getYearsBirthDate() {
        return birthDate.getYear();
    }

    private BigDecimal calculateMaximumValue() {
        return salary.multiply(ONE_THIRD_SALARY);
    }

    public BigDecimal getRate() {
        return modality.getRate();
    }
}
