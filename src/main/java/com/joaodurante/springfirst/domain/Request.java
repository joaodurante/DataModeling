package com.joaodurante.springfirst.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date time;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address address;

    @OneToMany(mappedBy = "id.request")
    private Set<RequestItem> requestItems = new HashSet<>();;

    public Request(){}
    public Request(Integer id, Date time, Customer customer, Address address) {
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
    public Set<RequestItem> getRequestItems() { return requestItems; }

    public void setId(Integer id) { this.id = id; }
    public void setTime(Date time) { this.time = time; }
    public void setAddress(Address address) { this.address = address; }
    public void setPayment(Payment payment) { this.payment = payment; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setRequestItems(Set<RequestItem> ordemItems) { this.requestItems = ordemItems; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id.equals(request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
