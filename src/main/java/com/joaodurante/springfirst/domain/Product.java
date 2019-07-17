package com.joaodurante.springfirst.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name="PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "id.product")
    private Set<DemandItem> demandItems = new HashSet<>();

    public Product(){}
    public Product(Integer id, String name, Double price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public List<Demand> getDemands(){
        List<Demand> list = new ArrayList<>();
        for(DemandItem i : this.demandItems){
            list.add(i.getDemand());
        }
        return list;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public Set<DemandItem> getDemandItems() { return demandItems; }
    public void setDemandItems(Set<DemandItem> ordemItems) { this.demandItems = ordemItems; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
