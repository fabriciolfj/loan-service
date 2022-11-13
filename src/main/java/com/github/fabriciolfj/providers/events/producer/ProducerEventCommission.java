package com.github.fabriciolfj.providers.events.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.providers.events.converter.ContractCommissionDTOConverter;
import com.github.fabriciolfj.providers.events.dto.ContractCommissionDTO;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class ProducerEventCommission {

    @Inject
    @Channel("commission-service")
    @OnOverflow(value = OnOverflow.Strategy.BUFFER)
    private MutinyEmitter<String> producer;

    @Inject
    private ObjectMapper mapper;

    public Uni<Void> process(final Uni<Contract> contract) {
        return contract
                .onItem()
                .transform(ContractCommissionDTOConverter::toDTO)
                .onItem()
                .transform(this::toJson)
                .onItem()
                .transformToUni(producer::send)
                .invoke(() -> log.info("Message send"));
    }

    private String toJson(final ContractCommissionDTO dto) {
        try {
            return mapper.writeValueAsString(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
