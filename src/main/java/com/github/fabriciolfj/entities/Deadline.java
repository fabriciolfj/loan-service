package com.github.fabriciolfj.entities;

import lombok.*;

import java.time.LocalDate;

import static com.github.fabriciolfj.util.Constants.DAYS_30;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Deadline {

    private Integer mounts;
    private LocalDate firstSalary;

    public Deadline(final Integer mounts) {
        this.mounts = mounts;
        this.firstSalary = LocalDate.now().plusDays(DAYS_30);
    }
}
