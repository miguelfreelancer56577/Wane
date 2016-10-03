package app.wane.com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import app.wane.com.model.PurchaseOrderDetail;
import app.wane.com.soport.HeaderResponse;

/**
 * Created by Maricruz on 03/10/2016.
 */
public class ListPurchaseOrderDetail extends HeaderResponse{

    @JsonProperty("podetail")
    public Object podetail;

    public ListPurchaseOrderDetail(String service, String type, String token, String option, String result, final int size, final List<PurchaseOrderDetail> listPurchaseOrderDetail) {
        super(service, type, token, option, result);
        podetail = new Object(){
            @JsonProperty("arraysize")
            public int arraysizeAnInt = size;
            @JsonProperty("array")
            public List<PurchaseOrderDetail> array = listPurchaseOrderDetail;
        };
    }

    public Object getPodetail() {
        return podetail;
    }

    public void setPodetail(Object podetail) {
        this.podetail = podetail;
    }

    @Override
    public String toString() {
        return "ListPurchaseOrderDetail{" +
                "podetail=" + podetail +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListPurchaseOrderDetail)) return false;
        if (!super.equals(o)) return false;

        ListPurchaseOrderDetail that = (ListPurchaseOrderDetail) o;

        return !(podetail != null ? !podetail.equals(that.podetail) : that.podetail != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (podetail != null ? podetail.hashCode() : 0);
        return result;
    }
}
