package app.wane.com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import app.wane.com.model.PurchaseOrder;
import app.wane.com.soport.HeaderResponse;

/**
 * Created by mangelt on 03/10/2016.
 */
public class ListPurchaseOrder extends HeaderResponse {

    @JsonProperty("polist")
    public PoList polist;

    public ListPurchaseOrder() {

    }

    public ListPurchaseOrder(String service, String type, String token, String option, String result, final int arraysize, final List<PurchaseOrder> array) {
        super(service, type, token, option, result);
        this.polist = new PoList(arraysize, array);
    }

    public PoList getPolist() {
        return polist;
    }

    public void setPolist(PoList polist) {
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

    public class PoList {
        @JsonProperty("arraysize")
        protected int sizePoList;
        @JsonProperty("array")
        protected List<PurchaseOrder> listPoList;

        public PoList() {
        }

        public PoList(int sizePoList, List<PurchaseOrder> listPoList) {
            this.sizePoList = sizePoList;
            this.listPoList = listPoList;
        }

        public int getSizePoList() {
            return sizePoList;
        }

        public void setSizePoList(int sizePoList) {
            this.sizePoList = sizePoList;
        }

        public List<PurchaseOrder> getListPoList() {
            return listPoList;
        }

        public void setListPoList(List<PurchaseOrder> listPoList) {
            this.listPoList = listPoList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PoList)) return false;

            PoList poList = (PoList) o;

            if (sizePoList != poList.sizePoList) return false;
            return !(listPoList != null ? !listPoList.equals(poList.listPoList) : poList.listPoList != null);

        }

        @Override
        public int hashCode() {
            int result = sizePoList;
            result = 31 * result + (listPoList != null ? listPoList.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "PoList{" +
                    "sizePoList=" + sizePoList +
                    ", listPoList=" + listPoList +
                    '}';
        }
    }
}
