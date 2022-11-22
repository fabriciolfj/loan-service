package com.github.fabriciolfj.providers.database.data;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "customer")
public class CustomerData extends PanacheEntity {

    private String document;
    @Column(name = "birth_date")
    private String birthDate;
    private BigDecimal salary;
    private Integer score;
    @OneToMany(orphanRemoval = true, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ContractData> contracts = new ArrayList<>();
}
