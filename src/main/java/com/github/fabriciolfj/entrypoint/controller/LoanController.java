package com.github.fabriciolfj.entrypoint.controller;

import com.github.fabriciolfj.busines.usecase.LoanSuggestionUseCase;
import com.github.fabriciolfj.entrypoint.converte.ContractDTOConverter;
import com.github.fabriciolfj.entrypoint.converte.ErrorResponseConverter;
import com.github.fabriciolfj.entrypoint.converte.FinancialDTOConverter;
import com.github.fabriciolfj.entrypoint.dto.request.CustomerRequest;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/api/v1/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanSuggestionUseCase loanSuggestionUseCase;

    @POST
    public Uni<Response> createLoan(@Valid final CustomerRequest request) {
        return Uni.createFrom().item(request)
                .invoke(() -> log.info("Request received: {}", request))
                .onItem()
                .transform(ContractDTOConverter::toEntity)
                .onItem()
                .transformToUni(loanSuggestionUseCase::execute)
                .onItem()
                .transform(c -> FinancialDTOConverter.toResponse(c.getFinancial()))
                .onItem()
                .transform(c -> Response.status(Response.Status.CREATED).entity(c).build())
                .onFailure()
                .recoverWithItem(e -> Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(ErrorResponseConverter.toResponse(e.getMessage()))
                        .build());
    }
}
