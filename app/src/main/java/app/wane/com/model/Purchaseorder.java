package app.wane.com.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by mangelt on 21/09/2016.
 */
public class PurchaseOrder implements Serializable {

    @JsonProperty("poid")
    private int poid;
    @JsonProperty("statusString")
    private String statusString;
    @JsonProperty("deliverydate")
    private String deliverydate;
    @JsonProperty("deliveryaddress")
    private String deliveryaddress;
    @JsonProperty("mapurl")
    private String mapurl;

    public PurchaseOrder() {
    }

    public PurchaseOrder(int poid, String statusString, String deliverydate, String deliveryaddress, String mapurl) {
        this.poid = poid;
        this.statusString = statusString;
        this.deliverydate = deliverydate;
        this.deliveryaddress = deliveryaddress;
        this.mapurl = mapurl;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
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
                ", statusString='" + statusString + '\'' +
                ", deliverydate='" + deliverydate + '\'' +
                ", deliveryaddress='" + deliveryaddress + '\'' +
                ", mapurl='" + mapurl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseOrder)) return false;

        PurchaseOrder that = (PurchaseOrder) o;

        if (poid != that.poid) return false;
        if (statusString != null ? !statusString.equals(that.statusString) : that.statusString != null)
            return false;
        if (deliverydate != null ? !deliverydate.equals(that.deliverydate) : that.deliverydate != null)
            return false;
        if (deliveryaddress != null ? !deliveryaddress.equals(that.deliveryaddress) : that.deliveryaddress != null)
            return false;
        return !(mapurl != null ? !mapurl.equals(that.mapurl) : that.mapurl != null);

    }

    @Override
    public int hashCode() {
        int result = poid;
        result = 31 * result + (statusString != null ? statusString.hashCode() : 0);
        result = 31 * result + (deliverydate != null ? deliverydate.hashCode() : 0);
        result = 31 * result + (deliveryaddress != null ? deliveryaddress.hashCode() : 0);
        result = 31 * result + (mapurl != null ? mapurl.hashCode() : 0);
        return result;
    }
}
