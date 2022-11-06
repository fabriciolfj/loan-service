package com.github.fabriciolfj.exceptions;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus;
import com.github.fabriciolfj.exceptions.enums.Errors;
import lombok.Getter;

@Getter
public class LoanInvalidException extends RuntimeException {

    private Integer status;

    public LoanInvalidException() {
        super(Errors.ERROR_04.getMessage());
        this.status = HttpStatus.SC_UNPROCESSABLE_ENTITY;
    }
}
