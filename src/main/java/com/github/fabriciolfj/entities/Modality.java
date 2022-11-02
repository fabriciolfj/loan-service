package com.github.fabriciolfj.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Modality {

    CONSIGNED("consigned", BigDecimal.valueOf(0.13)),
    RETIRED("retired", BigDecimal.valueOf(0.09)),
    PRIVATE("private", BigDecimal.valueOf(0.27));

    private String describe;
    private BigDecimal rate;

    public static Modality toEnum(final String describe) {
        return Stream.of(Modality.values())
                .filter(c -> c.describe.equals(describe))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Modality not found: " + describe));
    }

}
