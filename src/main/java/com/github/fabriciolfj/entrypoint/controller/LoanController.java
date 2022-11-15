package com.github.fabriciolfj.entrypoint.controller;

import com.github.fabriciolfj.busines.usecase.ListAllContractsUseCase;
import com.github.fabriciolfj.busines.usecase.LoanSuggestionUseCase;
import com.github.fabriciolfj.busines.usecase.QueryContractUseCase;
import com.github.fabriciolfj.entrypoint.converte.ContractDTOConverter;
import com.github.fabriciolfj.entrypoint.converte.DataResponseConverter;
import com.github.fabriciolfj.entrypoint.converte.ErrorResponseConverter;
import com.github.fabriciolfj.entrypoint.converte.FinancialDTOConverter;
import com.github.fabriciolfj.entrypoint.dto.request.CustomerRequest;
import com.github.fabriciolfj.entrypoint.dto.response.DataResponse;
import com.github.fabriciolfj.entrypoint.dto.response.FinancialResponse;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/api/v1/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanSuggestionUseCase loanSuggestionUseCase;
    private final QueryContractUseCase queryContractUseCase;
    private final ListAllContractsUseCase listAllContractsUseCase;

    @GET
    public Multi<DataResponse<FinancialResponse>> findAll(@QueryParam("page_init") final int pageInit) {
        return listAllContractsUseCase.execute(pageInit)
                .map(r -> DataResponseConverter.toDto(r, null))
                .onFailure()
                .recoverWithMulti(r -> Multi.createFrom().item(DataResponseConverter.toDto(null, r.getMessage())));
    }

    @GET
    @Path("/{code}")
    public Uni<Response> findContract(@PathParam("code") final String code) {
        return Uni.createFrom()
                .item(code)
                .invoke(() -> log.info("Request contract by code {}", code))
                .onItem()
                .transformToUni(queryContractUseCase::execute)
                .onItem()
                .transform(FinancialDTOConverter::toResponse)
                .onItem()
                .transform(r -> Response.accepted().entity(r).build())
                .onFailure()
                .recoverWithItem(e -> Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(ErrorResponseConverter.toResponse(e.getMessage()))
                        .build());
    }

    @POST
    public Uni<Response> createSuggestion(@Valid final CustomerRequest request) {
        return Uni.createFrom().item(request)
                .invoke(() -> log.info("Request received: {}", request))
                .onItem()
                .transform(ContractDTOConverter::toEntity)
                .onItem()
                .transformToUni(loanSuggestionUseCase::execute)
                .onItem()
                .transform(c -> FinancialDTOConverter.toResponse(c))
                .onItem()
                .transform(c -> Response.status(Response.Status.CREATED).entity(c).build())
                .onFailure()
                .recoverWithItem(e -> Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(ErrorResponseConverter.toResponse(e.getMessage()))
                        .build());
    }
}
