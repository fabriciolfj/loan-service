package com.github.fabriciolfj.entrypoint.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequest {

    @JsonProperty("portion")
    @NotNull(message = "Parcela nao informada")
    private Integer portion;
    @JsonProperty("value")
    @NotNull(message = "Valor do emprestimo nao informado")
    private BigDecimal value;
    @JsonProperty("document")
    @NotEmpty(message = "Documento nao informado")
    private String document;
    @JsonProperty("score")
    @NotNull(message = "Score nao informado")
    private Integer score;
    @JsonProperty("birth_date")
    @NotEmpty(message = "Data nascimento nao informada")
    private String birthDate;
    @JsonProperty("salary")
    @NotNull(message = "Salario nao informado")
    private BigDecimal salary;
    @JsonProperty("modality")
    @NotEmpty(message = "Modalidade ausente")
    private String modality;
    @NotEmpty(message = "Partner nao informado")
    private String partner;
}