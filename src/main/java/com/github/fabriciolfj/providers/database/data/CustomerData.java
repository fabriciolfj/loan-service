package com.github.fabriciolfj.providers.database.data;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerData {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private BigDecimal salary;
    private Integer score;
    @OneToMany(orphanRemoval = true, mappedBy = "customer")
    private List<ContractData> contracts;
}
