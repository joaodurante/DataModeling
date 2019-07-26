package com.joaodurante.springfirst.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.joaodurante.springfirst.domain.enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Payment implements Serializable {
    @Id
    private Integer id;
    private Integer state;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "demand_id")
    @MapsId
    private Demand demand;

    public Payment(){}
    public Payment(Integer id, PaymentState state, Demand demand) {
        super();
        this.id = id;
        this.state = (state == null) ? null : state.getCode();
        this.demand = demand;
    }

    public Integer getId() { return id; }
    public PaymentState getState() { return PaymentState.toEnum(this.state); }
    public Demand getDemand() { return demand; }

    public void setId(Integer id) { this.id = id; }
    public void setState(PaymentState state) { this.state = state.getCode(); }
    public void setDemand(Demand demand) { this.demand = demand; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
