package app.wane.com.model;

/**
 * Created by Maricruz on 24/09/2016.
 */
public class CatLastStatus {

    private int nIdLastStatus;
    private String sLastStatus;
    private String sDescription;

    public CatLastStatus(String sLastStatus, String sDescription) {
        this.sLastStatus = sLastStatus;
        this.sDescription = sDescription;
    }

    public CatLastStatus(int nIdLastStatus, String sLastStatus, String sDescription) {
        this.nIdLastStatus = nIdLastStatus;
        this.sLastStatus = sLastStatus;
        this.sDescription = sDescription;
    }

    public int getnIdLastStatus() {
        return nIdLastStatus;
    }

    public void setnIdLastStatus(int nIdLastStatus) {
        this.nIdLastStatus = nIdLastStatus;
    }

    public String getsLastStatus() {
        return sLastStatus;
    }

    public void setsLastStatus(String sLastStatus) {
        this.sLastStatus = sLastStatus;
    }

    public String getsDescription() {
        return sDescription;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CatLastStatus)) return false;

        CatLastStatus that = (CatLastStatus) o;

        if (nIdLastStatus != that.nIdLastStatus) return false;
        if (sLastStatus != null ? !sLastStatus.equals(that.sLastStatus) : that.sLastStatus != null)
            return false;
        return !(sDescription != null ? !sDescription.equals(that.sDescription) : that.sDescription != null);

    }

    @Override
    public int hashCode() {
        int result = nIdLastStatus;
        result = 31 * result + (sLastStatus != null ? sLastStatus.hashCode() : 0);
        result = 31 * result + (sDescription != null ? sDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CatLastStatus{" +
                "nIdLastStatus=" + nIdLastStatus +
                ", sLastStatus='" + sLastStatus + '\'' +
                ", sDescription='" + sDescription + '\'' +
                '}';
    }
}
