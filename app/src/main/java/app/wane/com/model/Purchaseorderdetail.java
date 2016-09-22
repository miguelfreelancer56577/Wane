package app.wane.com.model;

import java.util.Date;

/**
 * Created by mangelt on 21/09/2016.
 */
public class Purchaseorderdetail {
    private Long nidpurchaseorderdetail;
    private String ssku;
    private short namount;
    private boolean bdelivered;
    private boolean bcancelled;
    private boolean bisoptionb;
    private boolean bisdamage;
    private boolean brejected;
    private Integer nidvendor;
    private boolean bavailable;
    private Date dlastupdate;

    public Long getNidpurchaseorderdetail() {
        return nidpurchaseorderdetail;
    }

    public void setNidpurchaseorderdetail(Long nidpurchaseorderdetail) {
        this.nidpurchaseorderdetail = nidpurchaseorderdetail;
    }

    public String getSsku() {
        return ssku;
    }

    public void setSsku(String ssku) {
        this.ssku = ssku;
    }

    public short getNamount() {
        return namount;
    }

    public void setNamount(short namount) {
        this.namount = namount;
    }

    public boolean isBdelivered() {
        return bdelivered;
    }

    public void setBdelivered(boolean bdelivered) {
        this.bdelivered = bdelivered;
    }

    public boolean isBcancelled() {
        return bcancelled;
    }

    public void setBcancelled(boolean bcancelled) {
        this.bcancelled = bcancelled;
    }

    public boolean isBisoptionb() {
        return bisoptionb;
    }

    public void setBisoptionb(boolean bisoptionb) {
        this.bisoptionb = bisoptionb;
    }

    public boolean isBisdamage() {
        return bisdamage;
    }

    public void setBisdamage(boolean bisdamage) {
        this.bisdamage = bisdamage;
    }

    public boolean isBrejected() {
        return brejected;
    }

    public void setBrejected(boolean brejected) {
        this.brejected = brejected;
    }

    public Integer getNidvendor() {
        return nidvendor;
    }

    public void setNidvendor(Integer nidvendor) {
        this.nidvendor = nidvendor;
    }

    public boolean isBavailable() {
        return bavailable;
    }

    public void setBavailable(boolean bavailable) {
        this.bavailable = bavailable;
    }

    public Date getDlastupdate() {
        return dlastupdate;
    }

    public void setDlastupdate(Date dlastupdate) {
        this.dlastupdate = dlastupdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchaseorderdetail)) return false;

        Purchaseorderdetail that = (Purchaseorderdetail) o;

        if (getNamount() != that.getNamount()) return false;
        if (isBdelivered() != that.isBdelivered()) return false;
        if (isBcancelled() != that.isBcancelled()) return false;
        if (isBisoptionb() != that.isBisoptionb()) return false;
        if (isBisdamage() != that.isBisdamage()) return false;
        if (isBrejected() != that.isBrejected()) return false;
        if (isBavailable() != that.isBavailable()) return false;
        if (getNidpurchaseorderdetail() != null ? !getNidpurchaseorderdetail().equals(that.getNidpurchaseorderdetail()) : that.getNidpurchaseorderdetail() != null)
            return false;
        if (getSsku() != null ? !getSsku().equals(that.getSsku()) : that.getSsku() != null)
            return false;
        if (getNidvendor() != null ? !getNidvendor().equals(that.getNidvendor()) : that.getNidvendor() != null)
            return false;
        return !(getDlastupdate() != null ? !getDlastupdate().equals(that.getDlastupdate()) : that.getDlastupdate() != null);

    }

    @Override
    public int hashCode() {
        int result = getNidpurchaseorderdetail() != null ? getNidpurchaseorderdetail().hashCode() : 0;
        result = 31 * result + (getSsku() != null ? getSsku().hashCode() : 0);
        result = 31 * result + (int) getNamount();
        result = 31 * result + (isBdelivered() ? 1 : 0);
        result = 31 * result + (isBcancelled() ? 1 : 0);
        result = 31 * result + (isBisoptionb() ? 1 : 0);
        result = 31 * result + (isBisdamage() ? 1 : 0);
        result = 31 * result + (isBrejected() ? 1 : 0);
        result = 31 * result + (getNidvendor() != null ? getNidvendor().hashCode() : 0);
        result = 31 * result + (isBavailable() ? 1 : 0);
        result = 31 * result + (getDlastupdate() != null ? getDlastupdate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Purchaseorderdetail{" +
                "nidpurchaseorderdetail=" + nidpurchaseorderdetail +
                ", ssku='" + ssku + '\'' +
                ", namount=" + namount +
                ", bdelivered=" + bdelivered +
                ", bcancelled=" + bcancelled +
                ", bisoptionb=" + bisoptionb +
                ", bisdamage=" + bisdamage +
                ", brejected=" + brejected +
                ", nidvendor=" + nidvendor +
                ", bavailable=" + bavailable +
                ", dlastupdate=" + dlastupdate +
                '}';
    }
}
