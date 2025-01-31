package com.github.fabriciolfj.entrypoint.controller;

import com.github.fabriciolfj.busines.usecase.ApproveContractUseCase;
import com.github.fabriciolfj.busines.usecase.ListAllContractsUseCase;
import com.github.fabriciolfj.busines.usecase.LoanSuggestionUseCase;
import com.github.fabriciolfj.busines.usecase.QueryContractUseCase;
import com.github.fabriciolfj.entrypoint.converte.ContractDTOConverter;
import com.github.fabriciolfj.entrypoint.converte.ErrorResponseConverter;
import com.github.fabriciolfj.entrypoint.converte.FinancialDTOConverter;
import com.github.fabriciolfj.entrypoint.dto.request.CustomerRequest;
import com.github.fabriciolfj.entrypoint.dto.response.FinancialResponse;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.Duration;

@Slf4j
@Path("/api/v1/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanSuggestionUseCase loanSuggestionUseCase;
    private final QueryContractUseCase queryContractUseCase;
    private final ListAllContractsUseCase listAllContractsUseCase;
    private final ApproveContractUseCase approveContractUseCase;

    @GET
    public Multi<FinancialResponse> findAll(@QueryParam("page_init") final int pageInit) {
        return listAllContractsUseCase.execute(pageInit)
                .map(FinancialDTOConverter::toResponse)
                .ifNoItem()
                .after(Duration.ofSeconds(5))
                .recoverWithMulti(() -> Multi.createFrom().empty())
                .onFailure()
                .recoverWithMulti(() -> Multi.createFrom().empty());
    }

    @PUT
    @Path("/{code}/approve")
    public Uni<Response> approveContract(@PathParam("code") final String code) {
        return approveContractUseCase.execute(code)
                .onItem()
                .transform(c -> Response.noContent().build())
                .onFailure()
                .recoverWithItem(e -> Response.status(Response.Status.BAD_REQUEST)
                        .entity(ErrorResponseConverter.toResponse(e.getMessage()))
                        .build());
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
                .transform(FinancialDTOConverter::toResponse)
                .onItem()
                .transform(c -> Response.status(Response.Status.CREATED).entity(c).build())
                .onFailure()
                .recoverWithItem(e -> Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(ErrorResponseConverter.toResponse(e.getMessage()))
                        .build());
    }
}
