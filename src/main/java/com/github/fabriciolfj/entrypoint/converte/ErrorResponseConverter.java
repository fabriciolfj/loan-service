package com.github.fabriciolfj.entrypoint.converte;

import com.github.fabriciolfj.exceptions.model.ErrorResponse;

public class ErrorResponseConverter {

    private ErrorResponseConverter() { }

    public static ErrorResponse toResponse(final String message) {
        return ErrorResponse.builder()
                .message(message)
                .build();
    }
}
