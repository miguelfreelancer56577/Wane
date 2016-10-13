package app.wane.com.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import app.wane.com.soport.HeaderRequest;

/**
 * Created by Maricruz on 13/10/2016.
 */
public class StatusPurchase extends HeaderRequest {

    @JsonProperty("poid")
    protected String poid;

    public StatusPurchase(String option, String parameter, String poid) {
        super(option, parameter);
        this.poid = poid;
    }

    public String getPoid() {
        return poid;
    }

    public void setPoid(String poid) {
        this.poid = poid;
    }

    @Override
    public String toString() {
        return "StatusPurchase{" +
                "poid='" + poid + '\'' +
                '}' + "," +super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusPurchase)) return false;
        if (!super.equals(o)) return false;

        StatusPurchase that = (StatusPurchase) o;

        return !(poid != null ? !poid.equals(that.poid) : that.poid != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (poid != null ? poid.hashCode() : 0);
        return result;
    }
}
