package com.github.fabriciolfj.busines;

import com.github.fabriciolfj.entities.Contract;
import io.smallrye.mutiny.Uni;

public interface ProviderSaveContract {

    Uni<Contract> process(Uni<Contract> contract);
}
