package app.wane.com.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import app.wane.com.soport.HeaderResponse;

/**
 * Created by Maricruz on 03/10/2016.
 */
public class PanicButton extends HeaderResponse {

    @JsonProperty("pb")
    public Pb pb;

    public PanicButton() {

    }

    public PanicButton(String service, String type, String token, String option, String result, final String SelfStatus) {
        super(service, type, token, option, result);
        this.pb = new Pb(SelfStatus);
    }

    public Pb getPb() {
        return pb;
    }

    public void setPb(Pb pb) {
        this.pb = pb;
    }

    public class Pb {
        @JsonProperty("status")
        public String status;

        public Pb() {
        }

        public Pb(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Pb{" +
                    "status='" + status + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pb)) return false;

            Pb pb = (Pb) o;

            return !(status != null ? !status.equals(pb.status) : pb.status != null);

        }

        @Override
        public int hashCode() {
            return status != null ? status.hashCode() : 0;
        }
    }

    @Override
    public String toString() {


        return "PanicButton" + pb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PanicButton)) return false;
        if (!super.equals(o)) return false;

        PanicButton that = (PanicButton) o;

        return !(pb != null ? !pb.equals(that.pb) : that.pb != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (pb != null ? pb.hashCode() : 0);
        return result;
    }
}
