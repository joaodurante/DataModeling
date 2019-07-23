package com.joaodurante.springfirst.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.joaodurante.springfirst.domain.enums.CustomerType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String document;
    private Integer type;


    @OneToMany(mappedBy = "customer")
    private List<Address> address;

    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phones;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Demand> demands = new ArrayList<>();

    public Customer(){}
    public Customer(Integer id, String name, String email, String document, CustomerType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.type = (type==null) ? null : type.getCode();
        this.address = new ArrayList<>();
        this.phones = new HashSet<>();
    }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDocument(String document) { this.document = document; }
    public void setType(CustomerType type) { this.type = type.getCode(); }
    public void setPhones(Set<String> phones) { this.phones = phones; }
    public void setAddress(List<Address> address) { this.address = address; }
    public void setDemands(List<Demand> demands) { this.demands = demands; }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDocument() { return document; }
    public CustomerType getType() { return CustomerType.toEnum(this.type); }
    public Set<String> getPhones() { return phones; }
    public List<Address> getAddress() { return address; }
    public List<Demand> getDemands() { return demands; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

