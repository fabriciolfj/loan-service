package com.github.fabriciolfj.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.github.fabriciolfj.util.Constants.ONE_THIRD_SALARY;

public record Financial(Integer portion, BigDecimal loan, BigDecimal salary, Modality modality) {

    private static final Integer LESS_OR_EQUAL = 0;

    public boolean isLoanValid() {
        var value = calculateInstallment();
        var oneThird = calculateOneThird();

        return oneThird.compareTo(value) <= LESS_OR_EQUAL;
    }

    public BigDecimal calculateInstallment() {
        var value = loan.divide(BigDecimal.valueOf(portion), 4, RoundingMode.HALF_DOWN);
        return value.add(modality.calcFee(value));
    }

    private BigDecimal calculateOneThird() {
        return salary.multiply(ONE_THIRD_SALARY);
    }

}
