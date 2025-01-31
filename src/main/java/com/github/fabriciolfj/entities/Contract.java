package com.github.fabriciolfj.entities;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contract {

    private String code;
    private String partner;
    private StatusContract status;
    private LocalDate expirationDate;
    private LocalDateTime dateCreation;
    private Financial financial;
    private Deadline deadline;
    private Customer customer;
    private Loan loan;
    private Risk risk;

    public Integer getYearsBirthDate() {
        return LocalDate.now().getYear() - customer.getYearsBirthDate();
    }

    public Boolean isValid() {
        return financial.isLoanValid();
    }

    public Contract setDeadLine(final Deadline deadline) {
        this.deadline = deadline;
        return this;
    }

    public Contract init(final Customer customer, final Financial financial, final String partner) {
        this.customer = customer;
        this.financial = financial;
        this.status = StatusContract.PENDING;
        this.expirationDate = LocalDate.now();
        this.partner = partner;
        return this;
    }

    public Contract createLoan() {
        this.loan =  new Loan(financial.portion(), financial.calculateInstallment(), financial.loan(), financial.getTotalLoan());
        this.code = UUID.randomUUID().toString();
        this.dateCreation = LocalDateTime.now();
        return this;
    }

    public Contract approve() {
        this.status = StatusContract.APPROVED;
        return this;
    }

    public BigDecimal getInstallment() {
        return this.loan.installment();
    }

    public BigDecimal getLoanValue() {
        return this.loan.loan();
    }

    public String getDocument() {
        return this.customer.document();
    }

    public LocalDate getBirthDate() {
        return this.customer.birthDate();
    }

    public String getModality() {
        return this.financial.modality().getDescribe();
    }

    public BigDecimal getSalary() {
        return this.financial.salary();
    }

    public Integer getPortion() {
        return this.financial.portion();
    }

    public Integer getScore() {
        return this.customer.score();
    }

    public LocalDate getDueDate() {
        return this.deadline.getFirstSalary();
    }
}
