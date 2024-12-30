package com.github.fabriciolfj.exceptions;

import com.github.fabriciolfj.exceptions.enums.Errors;
import lombok.Getter;

import jakarta.ws.rs.core.Response;

@Getter
public class LoanInvalidException extends RuntimeException {

    private Integer status;

    public LoanInvalidException() {
        super(Errors.ERROR_04.getMessage());
        this.status = Response.Status.BAD_REQUEST.getStatusCode();
    }
}
