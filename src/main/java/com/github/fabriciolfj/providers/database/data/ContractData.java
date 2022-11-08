package com.github.fabriciolfj.providers.database.data;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContractData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private BigDecimal loan;
    private Integer portion;
    private String document;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    private BigDecimal salary;
    private String modality;
    private Integer score;
    private BigDecimal rate;
    private BigDecimal fees;
}
