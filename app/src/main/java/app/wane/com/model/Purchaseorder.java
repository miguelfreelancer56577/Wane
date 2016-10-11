package app.wane.com.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by mangelt on 21/09/2016.
 */
public class PurchaseOrder implements Serializable {

    @JsonProperty("poid")
    private int poid;
    @JsonProperty("statusid")
    private int statusid;
    @JsonProperty("status")
    private String status;
    @JsonProperty("deliverydate")
    private String deliverydate;
    @JsonProperty("deliveryaddress")
    private String deliveryaddress;
    @JsonProperty("mapurl")
    private String mapurl;
    @JsonProperty("color")
    private String color;

    public PurchaseOrder() {
    }

    public PurchaseOrder(int poid, int statusid, String status, String deliverydate, String deliveryaddress, String mapurl, String color) {
        this.poid = poid;
        this.statusid = statusid;
        this.status = status;
        this.deliverydate = deliverydate;
        this.deliveryaddress = deliveryaddress;
        this.mapurl = mapurl;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public String getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    public String getMapurl() {
        return mapurl;
    }

    public void setMapurl(String mapurl) {
        this.mapurl = mapurl;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "poid=" + poid +
                ", statusid=" + statusid +
                ", status='" + status + '\'' +
                ", deliverydate='" + deliverydate + '\'' +
                ", deliveryaddress='" + deliveryaddress + '\'' +
                ", mapurl='" + mapurl + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseOrder)) return false;

        PurchaseOrder that = (PurchaseOrder) o;

        if (getPoid() != that.getPoid()) return false;
        if (getStatusid() != that.getStatusid()) return false;
        if (getStatus() != null ? !getStatus().equals(that.getStatus()) : that.getStatus() != null)
            return false;
        if (getDeliverydate() != null ? !getDeliverydate().equals(that.getDeliverydate()) : that.getDeliverydate() != null)
            return false;
        if (getDeliveryaddress() != null ? !getDeliveryaddress().equals(that.getDeliveryaddress()) : that.getDeliveryaddress() != null)
            return false;
        if (getMapurl() != null ? !getMapurl().equals(that.getMapurl()) : that.getMapurl() != null)
            return false;
        return !(getColor() != null ? !getColor().equals(that.getColor()) : that.getColor() != null);

    }

    @Override
    public int hashCode() {
        int result = getPoid();
        result = 31 * result + getStatusid();
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getDeliverydate() != null ? getDeliverydate().hashCode() : 0);
        result = 31 * result + (getDeliveryaddress() != null ? getDeliveryaddress().hashCode() : 0);
        result = 31 * result + (getMapurl() != null ? getMapurl().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        return result;
    }
}
