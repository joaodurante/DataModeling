package com.joaodurante.springfirst.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class RequestItem implements Serializable {

    @EmbeddedId
    private RequestItemPK id = new RequestItemPK();;
    private Double discount;
    private Integer quantity;
    private Double price;

    public RequestItem(){}

    public RequestItem(Request request, Product product, Double discount, Integer quantity, Double price) {
        super();
        id.setRequest(request);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public RequestItemPK getId() {
        return id;
    }

    public Request getRequest(){ return id.getRequest(); }
    public Product getProduct(){ return id.getProduct(); }
    public void setId(RequestItemPK id) { this.id = id; }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestItem requestItem = (RequestItem) o;
        return id.equals(requestItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
