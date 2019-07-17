package com.joaodurante.springfirst.domain;


import com.joaodurante.springfirst.domain.enums.PaymentState;

import javax.persistence.Entity;

@Entity
public class CardPayment extends Payment {
    private Integer installments;

    public CardPayment(){}
    public CardPayment(Integer id, PaymentState state, Request request, Integer installments) {
        super(id, state, request);
        this.installments = installments;
    }

    public Integer getInstalltements() { return installments; }
    public void setInstalltements(Integer installments) { this.installments = installments; }
}
