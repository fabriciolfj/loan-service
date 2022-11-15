package com.github.fabriciolfj.busines.usecase;

import com.github.fabriciolfj.busines.ProviderEventContract;
import com.github.fabriciolfj.busines.ProviderFindContract;
import com.github.fabriciolfj.busines.ProviderSaveContract;
import com.github.fabriciolfj.entities.Contract;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class ApproveContractUseCase {

    private final ProviderFindContract findContract;
    private final ProviderSaveContract saveContract;
    private final ProviderEventContract eventContract;

    public Uni<Void> execute(final String code) {
        return findContract
                .process(code)
                .onItem()
                .transform(Contract::approve)
                .onItem()
                .transformToUni(saveContract::process)
                .onItem()
                .transformToUni(c -> eventContract.process(c));
    }
}
