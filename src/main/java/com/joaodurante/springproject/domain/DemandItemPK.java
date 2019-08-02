package com.joaodurante.springproject.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DemandItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "demand_id")
    private Demand demand;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Demand getDemand() { return demand; }
    public void setDemand(Demand demand) { this.demand = demand; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandItemPK that = (DemandItemPK) o;
        return demand.equals(that.demand) &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(demand, product);
    }
}
