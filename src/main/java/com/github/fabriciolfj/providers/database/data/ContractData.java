package com.github.fabriciolfj.providers.database.data;

import lombok.*;
import javax.persistence.*;
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
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private BigDecimal loan;
    private Integer portion;
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @Column(name = "due_date")
    private LocalDate dueDate;
    private String modality;
    private BigDecimal rate;
    private BigDecimal fees;
    private String status;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private CustomerData customer;

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
        return this.customer.getBirthDate();
    }
}
