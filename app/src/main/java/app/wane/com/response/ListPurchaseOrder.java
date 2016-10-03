package app.wane.com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import app.wane.com.model.PurchaseOrder;
import app.wane.com.soport.HeaderResponse;

/**
 * Created by Maricruz on 03/10/2016.
 */
public class ListPurchaseOrder extends HeaderResponse{

    @JsonProperty("polist")
    public Object polist;

    public ListPurchaseOrder(String service, String type, String token, String option, String result, final int sizePurchase,final List<PurchaseOrder> listPurchaseOrder) {
        super(service, type, token, option, result);
        this.polist = new Object(){
            @JsonProperty("arraysize")
            public int arraysize = sizePurchase;
            @JsonProperty("array")
            public List<PurchaseOrder> array = listPurchaseOrder;
        };
    }

    public Object getPolist() {
        return polist;
    }

    public void setPolist(Object polist) {
        this.polist = polist;
    }

    @Override
    public String toString() {
        return "ListPurchaseOrder{" +
                "polist=" + polist +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListPurchaseOrder)) return false;
        if (!super.equals(o)) return false;

        ListPurchaseOrder that = (ListPurchaseOrder) o;

        return !(polist != null ? !polist.equals(that.polist) : that.polist != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (polist != null ? polist.hashCode() : 0);
        return result;
    }
}
