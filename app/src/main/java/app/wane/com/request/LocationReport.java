package app.wane.com.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import app.wane.com.soport.HeaderRequest;

/**
 * Created by Maricruz on 03/10/2016.
 */
public class LocationReport extends HeaderRequest {

    @JsonProperty("location")
    public Object location;

    public LocationReport(String option, final String SelfLatitude, final String SelfLongitude) {
        super(option);
        this.location = new Object(){
            @JsonProperty("latitude")
            public String latitude = SelfLatitude;
            @JsonProperty("longitude")
            public String longitude = SelfLongitude;
        };
    }

    @Override
    public String toString() {
        return "LocationReport{" +
                "location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationReport)) return false;
        if (!super.equals(o)) return false;

        LocationReport that = (LocationReport) o;

        return !(location != null ? !location.equals(that.location) : that.location != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
