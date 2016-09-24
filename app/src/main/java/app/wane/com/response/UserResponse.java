package app.wane.com.response;

import app.wane.com.soport.Header;

/**
 * Created by mangelt on 23/09/2016.
 */
public class UserResponse extends Header {

    private String result;

    public UserResponse(String service, String type, String device, String token, String option, String result) {
        super(service, type, device, token, option);
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "result='" + result + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponse)) return false;
        if (!super.equals(o)) return false;

        UserResponse that = (UserResponse) o;

        return !(result != null ? !result.equals(that.result) : that.result != null);

    }

    @Override
    public int hashCode() {
        int result1 = super.hashCode();
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
