package app.wane.com.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by mangelt on 21/09/2016.
 */
public class PurchaseOrderDetail implements Serializable {

    @JsonProperty("poid")
    protected int poid;
    @JsonProperty("sku")
    protected String sku;
    @JsonProperty("quantity")
    protected String quantity;
    @JsonProperty("shortdescription")
    protected String shortdescription;

    public PurchaseOrderDetail() {
    }

    public PurchaseOrderDetail(String sku, String quantity, String shortdescription) {
        this.sku = sku;
        this.quantity = quantity;
        this.shortdescription = shortdescription;
    }

    public PurchaseOrderDetail(int poid, String sku, String quantity, String shortdescription) {
        this.poid = poid;
        this.sku = sku;
        this.quantity = quantity;
        this.shortdescription = shortdescription;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Override
    public String toString() {
        return "PurchaseOrderDetail{" +
                "poid=" + poid +
                ", sku='" + sku + '\'' +
                ", quantity='" + quantity + '\'' +
                ", shortdescription='" + shortdescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseOrderDetail)) return false;

        PurchaseOrderDetail that = (PurchaseOrderDetail) o;

        if (poid != that.poid) return false;
        if (sku != null ? !sku.equals(that.sku) : that.sku != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null)
            return false;
        return !(shortdescription != null ? !shortdescription.equals(that.shortdescription) : that.shortdescription != null);

    }

    @Override
    public int hashCode() {
        int result = poid;
        result = 31 * result + (sku != null ? sku.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (shortdescription != null ? shortdescription.hashCode() : 0);
        return result;
    }
}