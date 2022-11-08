package com.github.fabriciolfj.exceptions;

import com.github.fabriciolfj.exceptions.enums.Errors;
import lombok.Getter;

import javax.ws.rs.core.Response;

@Getter
public class SaveContractException extends RuntimeException {

    private Integer status;

    public SaveContractException() {
        super(Errors.ERROR_04.getMessage());
        this.status = Response.Status.BAD_REQUEST.getStatusCode();
    }
}
