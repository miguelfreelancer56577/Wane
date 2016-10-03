package app.wane.com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import app.wane.com.soport.HeaderResponse;

/**
 * Created by Maricruz on 03/10/2016.
 */
public class ChangeStatusPurchaseOrder extends HeaderResponse {

    @JsonProperty("pb")
    public Object pb;

    public ChangeStatusPurchaseOrder(String service, String type, String token, String option, String result, final String SelfStatus) {
        super(service, type, token, option, result);
        this.pb = new Object(){
            @JsonProperty("status")
            public String status = SelfStatus;
        };
    }

    public Object getPb() {
        return pb;
    }

    public void setPb(Object pb) {
        this.pb = pb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChangeStatusPurchaseOrder)) return false;
        if (!super.equals(o)) return false;

        ChangeStatusPurchaseOrder that = (ChangeStatusPurchaseOrder) o;

        return !(pb != null ? !pb.equals(that.pb) : that.pb != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (pb != null ? pb.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChangeStatusPurchaseOrder{" +
                "pb=" + pb +
                '}';
    }
}
