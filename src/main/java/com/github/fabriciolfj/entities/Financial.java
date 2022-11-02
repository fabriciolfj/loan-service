package com.github.fabriciolfj.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Financial(Integer portion, BigDecimal value, LocalDate firstSalary) {
}
