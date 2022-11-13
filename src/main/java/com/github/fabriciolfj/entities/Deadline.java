package com.github.fabriciolfj.entities;

import lombok.*;

import java.time.LocalDate;

import static com.github.fabriciolfj.util.Constants.DAYS_30;

@Getter
@Setter
@ToString
public class Deadline {

    private LocalDate firstSalary;

    public Deadline(final LocalDate localDate) {
        this.firstSalary = localDate;
    }

    public Deadline() {
        this.firstSalary = LocalDate.now().plusDays(DAYS_30);
    }
}
