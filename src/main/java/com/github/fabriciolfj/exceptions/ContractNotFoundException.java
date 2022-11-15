package com.github.fabriciolfj.exceptions;

import com.github.fabriciolfj.exceptions.enums.Errors;
import lombok.Getter;

import javax.ws.rs.core.Response;

@Getter
public class ContractNotFoundException extends RuntimeException {

    private Integer status;

    public ContractNotFoundException() {
        super(Errors.ERROR_05.getMessage());
        this.status = Response.Status.BAD_REQUEST.getStatusCode();
    }
}
