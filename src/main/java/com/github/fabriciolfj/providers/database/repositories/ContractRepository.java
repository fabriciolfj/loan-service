package com.github.fabriciolfj.providers.database.repositories;

import com.github.fabriciolfj.busines.ProviderFindContract;
import com.github.fabriciolfj.busines.ProviderSaveContract;
import com.github.fabriciolfj.busines.ProviderListContract;
import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.exceptions.ContractNotFoundException;
import com.github.fabriciolfj.providers.database.converter.ContractDataConverter;
import com.github.fabriciolfj.providers.database.data.ContractData;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;

@ApplicationScoped
@Slf4j
public class ContractRepository implements ProviderFindContract, ProviderSaveContract, ProviderListContract {

    private static final int PAGE_SIZE = 3;

    @Override
    public Uni<Contract> process(final String code) {
        return Uni.createFrom().item(code).onItem()
                .transformToUni(c -> ContractData.find("code", code).firstResult())
                .onItem()
                .transform(c -> (ContractData) c)
                .onItem()
                .transform(ContractDataConverter::toEntity)
                .ifNoItem()
                .after(Duration.ofSeconds(1))
                .failWith(new ContractNotFoundException());
    }

    @Override
    public Uni<Contract> process(final Contract contract) {
        return Uni.createFrom().item(contract).onItem()
                .transform(ContractDataConverter::toData)
                .onItem()
                .transformToUni(c -> Panache.withTransaction(() -> c.persist()))
                .invoke(c -> log.info("Contract saved: {}", c))
                .onItem()
                .transform(result -> contract)
                .onFailure()
                .invoke(e -> log.error("Fail saved contract. Details {}", e.getMessage()));
    }

    @Override
    public Multi<Contract> process(int pageInit) {
        return ContractData.findAll()
                .page(pageInit, PAGE_SIZE)
                .list()
                .onItem()
                .transformToMulti(r -> Multi.createFrom().items(r.toArray()))
                .onItem()
                .transform(c -> ContractDataConverter.toEntity((ContractData) c));
    }
}
