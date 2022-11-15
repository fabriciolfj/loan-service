package com.github.fabriciolfj.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum StatusContract {
    APPROVED("approved"), PENDING("pending");

    private final String describe;

    public static StatusContract toEnum(final String describe) {
        return Stream.of(StatusContract.values())
                .filter(c -> c.describe.equals(describe))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Status contract not found " + describe));
    }
}
