package app.wane.com.soport;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by mangelt on 23/09/2016.
 */
public class HeaderRequest implements Serializable {
    @JsonProperty("service")
    protected String service;
    @JsonProperty("type")
    protected String type;
    @JsonProperty("token")
    protected String token;
    @JsonProperty("option")
    protected String option;
    @JsonProperty("parameter")
    protected String parameter;

    private HeaderRequest(){
        this.service = "messenger";
        this.type = "request";
        this.token = TokenRest.val;
        this.parameter = "";
    }

    public HeaderRequest(String option) {
        this();
        this.option = option;
    }

    public HeaderRequest(String option, String parameter) {
        this();
        this.option = option;
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
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
    public String toString() {
        return "HeaderRequest{" +
                "service='" + service + '\'' +
                ", type='" + type + '\'' +
                ", token='" + token + '\'' +
                ", option='" + option + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeaderRequest)) return false;

        HeaderRequest that = (HeaderRequest) o;

        if (service != null ? !service.equals(that.service) : that.service != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        return !(option != null ? !option.equals(that.option) : that.option != null);

    }

    @Override
    public int hashCode() {
        int result = service != null ? service.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (option != null ? option.hashCode() : 0);
        return result;
    }
}
