package app.wane.com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import app.wane.com.model.PurchaseOrderDetail;
import app.wane.com.soport.HeaderResponse;

/**
 * Created by Maricruz on 03/10/2016.
 */
public class ListPurchaseOrderDetail extends HeaderResponse {

    @JsonProperty("podetail")
    public PoDetail podetail;

    public ListPurchaseOrderDetail() {

    }

    public ListPurchaseOrderDetail(String service, String type, String token, String option, String result, final int size, final List<PurchaseOrderDetail> listPurchaseOrderDetail) {
        super(service, type, token, option, result);
        podetail = new PoDetail(size, listPurchaseOrderDetail);
    }

    public PoDetail getPodetail() {
        return podetail;
    }

    public void setPodetail(PoDetail podetail) {
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

    public class PoDetail {

        @JsonProperty("arraysize")
        public int arraysize;
        @JsonProperty("array")
        public List<PurchaseOrderDetail> array;

        public PoDetail() {
        }

        public PoDetail(int arraysize, List<PurchaseOrderDetail> array) {
            this.arraysize = arraysize;
            this.array = array;
        }

        public int getArraysize() {
            return arraysize;
        }

        public void setArraysize(int arraysize) {
            this.arraysize = arraysize;
        }

        public List<PurchaseOrderDetail> getArray() {
            return array;
        }

        public void setArray(List<PurchaseOrderDetail> array) {
            this.array = array;
        }

        @Override
        public String toString() {
            return "PoDetail{" +
                    "arraysize=" + arraysize +
                    ", array=" + array +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PoDetail)) return false;

            PoDetail poDetail = (PoDetail) o;

            if (arraysize != poDetail.arraysize) return false;
            return !(array != null ? !array.equals(poDetail.array) : poDetail.array != null);

        }

        @Override
        public int hashCode() {
            int result = arraysize;
            result = 31 * result + (array != null ? array.hashCode() : 0);
            return result;
        }
    }

}
