package com.github.fabriciolfj.exceptions.mapper;

import com.github.fabriciolfj.exceptions.model.MessageErrorDetails;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class ConstraintExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private ConstraintViolation<?> cv;

    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(prepareMessage(e))
                .build();
    }

    private List<MessageErrorDetails> prepareMessage(ConstraintViolationException exception) {
        final List<MessageErrorDetails> messages = new ArrayList<>();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            messages.add(new MessageErrorDetails(cv.getPropertyPath().toString(), cv.getMessage()));
        }
        return messages;
    }
}
