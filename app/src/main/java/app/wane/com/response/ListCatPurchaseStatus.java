package app.wane.com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import app.wane.com.model.CatPurchaseOrder;
import app.wane.com.soport.HeaderResponse;

/**
 * Created by Maricruz on 03/10/2016.
 */
public class ListCatPurchaseStatus extends HeaderResponse {

    @JsonProperty("postatuscatalog")
    public Object postatuscatalog;

    public ListCatPurchaseStatus(String service, String type, String token, String option, String result, final int size, final List<CatPurchaseOrder> listCatPurchaseOrder) {
        super(service, type, token, option, result);
        this.postatuscatalog = new Object(){
            @JsonProperty("arraysize")
            public int arraysize = size;
            @JsonProperty("array")
            public List<CatPurchaseOrder> array = listCatPurchaseOrder;
        };
    }

    public Object getPostatuscatalog() {
        return postatuscatalog;
    }

    public void setPostatuscatalog(Object postatuscatalog) {
        this.postatuscatalog = postatuscatalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListCatPurchaseStatus)) return false;
        if (!super.equals(o)) return false;

        ListCatPurchaseStatus that = (ListCatPurchaseStatus) o;

        return !(postatuscatalog != null ? !postatuscatalog.equals(that.postatuscatalog) : that.postatuscatalog != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (postatuscatalog != null ? postatuscatalog.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ListCatPurchaseStatus{" +
                "postatuscatalog=" + postatuscatalog +
                '}';
    }
}