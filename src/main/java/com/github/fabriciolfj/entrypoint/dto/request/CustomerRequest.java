package com.github.fabriciolfj.entrypoint.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequest {

    @JsonProperty("name")
    @NotEmpty(message = "${customer.name}")
    private String name;
    @JsonProperty("score")
    @NotNull(message = "${customer.score}")
    private Integer score;
    @JsonProperty("birth_date")
    @NotEmpty(message = "${customer.birthDate}")
    private LocalDate birthDate;
    @JsonProperty("salary")
    @NotNull(message = "${customer.salary}")
    private BigDecimal salary;
    @JsonProperty("modality")
    @NotEmpty(message = "${customer.modality}")
    private String modality;
}