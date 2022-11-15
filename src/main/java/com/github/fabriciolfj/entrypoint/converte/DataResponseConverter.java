package com.github.fabriciolfj.entrypoint.converte;

import com.github.fabriciolfj.entities.Contract;
import com.github.fabriciolfj.entrypoint.dto.response.DataResponse;
import com.github.fabriciolfj.entrypoint.dto.response.FinancialResponse;

public class DataResponseConverter {

    private DataResponseConverter() { }

    public static DataResponse<FinancialResponse> toDto(final Contract contract, final String message) {
        var dto = FinancialDTOConverter.toResponse(contract);
        var data = new DataResponse<FinancialResponse>();
        data.setData(dto);
        data.setMessage(message);

        return data;
    }
}
