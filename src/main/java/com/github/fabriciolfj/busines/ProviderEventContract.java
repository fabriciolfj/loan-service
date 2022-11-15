package com.github.fabriciolfj.busines;

import com.github.fabriciolfj.entities.Contract;
import io.smallrye.mutiny.Uni;

public interface ProviderEventContract {

    Uni<Void> process(final Contract contract);
}
