package app.wane.com.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import app.wane.com.model.User;
import app.wane.com.soport.HeaderRequest;


/**
 * Created by mangelt on 23/09/2016.
 */
public class Messenger extends HeaderRequest{

    @JsonProperty("messenger")
    protected User messenger;

    public Messenger(String option, User messenger) {
        super(option);
        this.messenger = messenger;
    }

    public User getMessenger() {
        return messenger;
    }

    public void setMessenger(User messenger) {
        this.messenger = messenger;
    }

    @Override
    public String toString() {
        return "Messenger{" +
                "messenger=" + messenger +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Messenger)) return false;
        if (!super.equals(o)) return false;

        Messenger messenger1 = (Messenger) o;

        return !(messenger != null ? !messenger.equals(messenger1.messenger) : messenger1.messenger != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (messenger != null ? messenger.hashCode() : 0);
        return result;
    }
}

