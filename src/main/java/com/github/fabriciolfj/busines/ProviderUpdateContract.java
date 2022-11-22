package com.github.fabriciolfj.busines;

import com.github.fabriciolfj.entities.Contract;
import io.smallrye.mutiny.Uni;

public interface ProviderUpdateContract {

    Uni<Contract> processUpdateStatus(final Contract contract);
}
