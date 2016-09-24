package app.wane.com.request;

import app.wane.com.model.User;
import app.wane.com.soport.HeaderRequest;


/**
 * Created by mangelt on 23/09/2016.
 */
public class UserRequest extends HeaderRequest{
    protected User user;

    public UserRequest(String service, String type, String device, String token, String option, User user) {
        super(service, type, device, token, option);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRequest)) return false;
        if (!super.equals(o)) return false;

        UserRequest that = (UserRequest) o;

        return user.equals(that.user);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}

