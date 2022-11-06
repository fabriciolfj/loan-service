package com.github.fabriciolfj.entities;

import java.time.LocalDate;


public record Customer(String document, Integer score, LocalDate birthDate) {

    public Integer getYearsBirthDate() {
        return birthDate.getYear();
    }
}
