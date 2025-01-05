package com.github.fabriciolfj.providers.database.data;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "contract")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class ContractData extends PanacheEntity {

    private String code;
    private BigDecimal loan;
    private Integer portion;
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @Column(name = "date_expiration")
    private String dateExpiration;
    @Column(name = "due_date")
    private String dueDate;
    private String modality;
    private BigDecimal rate;
    private BigDecimal fees;
    private String status;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    private CustomerData customer;
    @Column(name = "installment_value", nullable = false, precision = 4)
    private BigDecimal installmentValue;
    @Column(name = "total_loan", nullable = false, precision = 4)
    private BigDecimal totalLoan;
    @Column(name = "partner")
    private String partner;

    public Integer getScore() {
        return this.customer.getScore();
    }

    public String getDocument() {
        return this.customer.getDocument();
    }

    public BigDecimal getSalary() {
        return this.customer.getSalary();
    }

    public LocalDate getBirthDate() {
        return LocalDate.parse(this.customer.getBirthDate());
    }
}
