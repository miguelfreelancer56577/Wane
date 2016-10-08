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
    public PoStatusCatalog postatuscatalog;

    public ListCatPurchaseStatus() {

    }

    public ListCatPurchaseStatus(String service, String type, String token, String option, String result, final int arraysize, final List<CatPurchaseOrder> array) {
        super(service, type, token, option, result);
        postatuscatalog = new PoStatusCatalog(arraysize, array);
    }

    public PoStatusCatalog getPostatuscatalog() {
        return postatuscatalog;
    }

    public void setPostatuscatalog(PoStatusCatalog postatuscatalog) {
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

    public class PoStatusCatalog {

        @JsonProperty("arraysize")
        protected int arraysize;
        @JsonProperty("array")
        protected List<CatPurchaseOrder> array;

        public PoStatusCatalog() {
        }

        public PoStatusCatalog(int arraysize, List<CatPurchaseOrder> array) {
            this.arraysize = arraysize;
            this.array = array;
        }

        public int getArraysize() {
            return arraysize;
        }

        public void setArraysize(int arraysize) {
            this.arraysize = arraysize;
        }

        public List<CatPurchaseOrder> getArray() {
            return array;
        }

        public void setArray(List<CatPurchaseOrder> array) {
            this.array = array;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PoStatusCatalog)) return false;

            PoStatusCatalog that = (PoStatusCatalog) o;

            if (arraysize != that.arraysize) return false;
            return !(array != null ? !array.equals(that.array) : that.array != null);

        }

        @Override
        public int hashCode() {
            int result = arraysize;
            result = 31 * result + (array != null ? array.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "PoStatusCatalog{" +
                    "arraysize=" + arraysize +
                    ", array=" + array +
                    '}';
        }
    }
}