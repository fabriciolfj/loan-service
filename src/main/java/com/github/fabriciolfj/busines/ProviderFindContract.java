package com.github.fabriciolfj.busines;

import com.github.fabriciolfj.entities.Contract;
import io.smallrye.mutiny.Uni;

public interface ProviderFindContract {

    Uni<Contract> findByContract(final Uni<String> code);
}
