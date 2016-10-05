package app.wane.com.soport;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Maricruz on 23/09/2016.
 */
public class HeaderResponse implements Serializable {
    @JsonProperty("service")
    protected String service;
    @JsonProperty("type")
    protected String type;
    @JsonProperty("token")
    protected String token;
    @JsonProperty("option")
    protected String option;
    @JsonProperty("result")
    protected String result;

    public HeaderResponse() {
    }

    public HeaderResponse(String service, String type, String token, String option, String result) {
        this.service = service;
        this.type = type;
        this.token = token;
        this.option = option;
        this.result = result;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HeaderResponse{" +
                "service='" + service + '\'' +
                ", type='" + type + '\'' +
                ", token='" + token + '\'' +
                ", option='" + option + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeaderResponse)) return false;

        HeaderResponse that = (HeaderResponse) o;

        if (service != null ? !service.equals(that.service) : that.service != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (option != null ? !option.equals(that.option) : that.option != null) return false;
        return !(result != null ? !result.equals(that.result) : that.result != null);

    }

    @Override
    public int hashCode() {
        int result1 = service != null ? service.hashCode() : 0;
        result1 = 31 * result1 + (type != null ? type.hashCode() : 0);
        result1 = 31 * result1 + (token != null ? token.hashCode() : 0);
        result1 = 31 * result1 + (option != null ? option.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
