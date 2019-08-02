package com.joaodurante.springproject.domain;


import com.fasterxml.jackson.annotation.JsonTypeName;
import com.joaodurante.springproject.domain.enums.PaymentState;

import javax.persistence.Entity;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {
    private Integer installments;

    public CardPayment(){}
    public CardPayment(Integer id, PaymentState state, Demand demand, Integer installments) {
        super(id, state, demand);
        this.installments = installments;
    }

    public Integer getInstallments() { return installments; }
    public void setInstallments(Integer installments) { this.installments = installments; }
}
