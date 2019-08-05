package com.joaodurante.springproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class DemandItem implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private DemandItemPK id = new DemandItemPK();

    private Double discount;
    private Integer quantity;
    private Double price;

    public DemandItem(){}

    public DemandItem(Demand demand, Product product, Double discount, Integer quantity, Double price) {
        super();
        id.setDemand(demand);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public DemandItemPK getId() {
        return id;
    }

    @JsonIgnore
    public Demand getDemand(){ return id.getDemand(); }

    public void setDemand(Demand demand){ this.id.setDemand(demand); }

    public Product getProduct(){ return id.getProduct(); }

    public void setProduct(Product product) { this.id.setProduct(product); }

    public void setId(DemandItemPK id) { this.id = id; }

    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getSubtotal(){
        return (price - discount) * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandItem demandItem = (DemandItem) o;
        return id.equals(demandItem.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        sb.append(this.getProduct().getName());
        sb.append(", Qtt: ");
        sb.append(this.getQuantity());
        sb.append(", Unit price: ");
        sb.append(nf.format(this.getPrice()));
        sb.append(", Subtotal: ");
        sb.append(nf.format(this.getSubtotal()));
        sb.append("\n");
        return sb.toString();
    }
}
