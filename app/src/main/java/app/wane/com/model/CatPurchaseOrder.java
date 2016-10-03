package app.wane.com.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Maricruz on 24/09/2016.
 */
public class CatPurchaseOrder implements Serializable {

    @JsonProperty("statusid")
    protected int statusid;
    @JsonProperty("status")
    protected String status;
    @JsonProperty("description")
    protected String description;

    public CatPurchaseOrder() {
    }

    public CatPurchaseOrder(int statusid, String status, String description) {
        this.statusid = statusid;
        this.status = status;
        this.description = description;
    }

    @Override
    public String toString() {
        return "CatPurchaseOrder{" +
                "statusid=" + statusid +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CatPurchaseOrder)) return false;

        CatPurchaseOrder that = (CatPurchaseOrder) o;

        if (statusid != that.statusid) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return !(description != null ? !description.equals(that.description) : that.description != null);

    }

    @Override
    public int hashCode() {
        int result = statusid;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
