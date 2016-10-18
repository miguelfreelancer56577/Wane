package app.wane.com.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import app.wane.com.soport.HeaderRequest;

/**
 * Created by Maricruz on 03/10/2016.
 */
public class LocationReport extends HeaderRequest {

    @JsonProperty("location")
    public Location location;

    public LocationReport(String option, final String SelfLatitude, final String SelfLongitude) {
        super(option);
        this.location = new Location(SelfLatitude, SelfLongitude);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public class Location{
        @JsonProperty("latitude")
        public String latitude;
        @JsonProperty("longitude")
        public String longitude;

        public Location(){}

        public Location(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Location)) return false;

            Location location = (Location) o;

            if (latitude != null ? !latitude.equals(location.latitude) : location.latitude != null)
                return false;
            return !(longitude != null ? !longitude.equals(location.longitude) : location.longitude != null);

        }

        @Override
        public int hashCode() {
            int result = latitude != null ? latitude.hashCode() : 0;
            result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    '}';
        }
    }
}
