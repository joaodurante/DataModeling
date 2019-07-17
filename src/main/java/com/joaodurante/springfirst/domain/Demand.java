package com.joaodurante.springfirst.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Demand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date time;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "demand")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address address;

    @OneToMany(mappedBy = "id.demand")
    private Set<DemandItem> demandItems = new HashSet<>();;

    public Demand(){}
    public Demand(Integer id, Date time, Customer customer, Address address) {
        this.id = id;
        this.time = time;
        this.customer = customer;
        this.address = address;
    }

    public Integer getId() { return id; }
    public Date getTime() { return time; }
    public Address getAddress() { return address; }
    public Payment getPayment() { return payment; }
    public Customer getCustomer() { return customer; }
    public Set<DemandItem> getDemandItems() { return demandItems; }

    public void setId(Integer id) { this.id = id; }
    public void setTime(Date time) { this.time = time; }
    public void setAddress(Address address) { this.address = address; }
    public void setPayment(Payment payment) { this.payment = payment; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setDemandItems(Set<DemandItem> ordemItems) { this.demandItems = ordemItems; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demand demand = (Demand) o;
        return id.equals(demand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
