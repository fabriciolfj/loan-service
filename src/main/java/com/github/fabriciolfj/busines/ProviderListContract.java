package com.github.fabriciolfj.busines;

import com.github.fabriciolfj.entities.Contract;
import io.smallrye.mutiny.Multi;

public interface ProviderListContract {

    Multi<Contract> process(final int pageInit);
}
