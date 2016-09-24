package app.wane.com.soport;

/**
 * Created by Maricruz on 23/09/2016.
 */
public class HeaderResponse {
    protected String service;
    protected String type;
    protected String device;
    protected String token;
    protected String option;

    public HeaderResponse(String service, String type, String device, String token, String option) {
        this.service = service;
        this.type = type;
        this.device = device;
        this.token = token;
        this.option = option;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeaderResponse)) return false;

        HeaderResponse header = (HeaderResponse) o;

        if (!getService().equals(header.getService())) return false;
        if (!getType().equals(header.getType())) return false;
        if (!getDevice().equals(header.getDevice())) return false;
        if (!getToken().equals(header.getToken())) return false;
        return getOption().equals(header.getOption());

    }

    @Override
    public int hashCode() {
        int result = getService().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getDevice().hashCode();
        result = 31 * result + getToken().hashCode();
        result = 31 * result + getOption().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Header{" +
                "service='" + service + '\'' +
                ", type='" + type + '\'' +
                ", device='" + device + '\'' +
                ", token='" + token + '\'' +
                ", option='" + option + '\'' +
                '}';
    }
}
