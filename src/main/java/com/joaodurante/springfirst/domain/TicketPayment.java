package com.joaodurante.springfirst.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joaodurante.springfirst.domain.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class TicketPayment extends Payment {

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date payday;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date duedate;

    public TicketPayment(){}
    public TicketPayment(Integer id, PaymentState state, Request request, Date payday, Date duedate) {
        super(id, state, request);
        this.payday = payday;
        this.duedate = duedate;
    }

    public Date getPayday() { return payday; }
    public Date getDuedate() { return duedate; }

    public void setPayday(Date payday) { this.payday = payday; }
    public void setDuedate(Date duedate) { this.duedate = duedate; }
}
