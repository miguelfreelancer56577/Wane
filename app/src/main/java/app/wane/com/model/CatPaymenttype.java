package app.wane.com.model;

/**
 * Created by mangelt on 21/09/2016.
 */
public class CatPaymenttype {
    private Integer nidpaymenttype;
    private String spaymenttype;
    private String sdescription;

    public Integer getNidpaymenttype() {
        return nidpaymenttype;
    }

    public void setNidpaymenttype(Integer nidpaymenttype) {
        this.nidpaymenttype = nidpaymenttype;
    }

    public String getSpaymenttype() {
        return spaymenttype;
    }

    public void setSpaymenttype(String spaymenttype) {
        this.spaymenttype = spaymenttype;
    }

    public String getSdescription() {
        return sdescription;
    }

    public void setSdescription(String sdescription) {
        this.sdescription = sdescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CatPaymenttype)) return false;

        CatPaymenttype that = (CatPaymenttype) o;

        if (!getNidpaymenttype().equals(that.getNidpaymenttype())) return false;
        if (!getSpaymenttype().equals(that.getSpaymenttype())) return false;
        return getSdescription().equals(that.getSdescription());

    }

    @Override
    public int hashCode() {
        int result = getNidpaymenttype().hashCode();
        result = 31 * result + getSpaymenttype().hashCode();
        result = 31 * result + getSdescription().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CatPaymenttype{" +
                "nidpaymenttype=" + nidpaymenttype +
                ", spaymenttype='" + spaymenttype + '\'' +
                ", sdescription='" + sdescription + '\'' +
                '}';
    }
}

