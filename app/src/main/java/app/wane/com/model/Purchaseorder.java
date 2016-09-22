package app.wane.com.model;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

/**
 * Created by mangelt on 21/09/2016.
 */
public class Purchaseorder {
    private Long nidpurchaseorder;
    private CatPaymenttype catPaymenttype;
    private int nidclient;
    private int nidmessenger;
    private int niddeliveryaddress;
    private Short deta;
    private boolean binvoiced;
    private boolean bisterminalpayment;
    private Date dlastupdate;
    private Integer nidstatus;
    private String sinvoiceid;
    private Set<Purchaseorderdetail> purchaseorderdetails = new HashSet<Purchaseorderdetail>(0);

    public Long getNidpurchaseorder() {
        return nidpurchaseorder;
    }

    public void setNidpurchaseorder(Long nidpurchaseorder) {
        this.nidpurchaseorder = nidpurchaseorder;
    }

    public CatPaymenttype getCatPaymenttype() {
        return catPaymenttype;
    }

    public void setCatPaymenttype(CatPaymenttype catPaymenttype) {
        this.catPaymenttype = catPaymenttype;
    }

    public int getNidclient() {
        return nidclient;
    }

    public void setNidclient(int nidclient) {
        this.nidclient = nidclient;
    }

    public int getNidmessenger() {
        return nidmessenger;
    }

    public void setNidmessenger(int nidmessenger) {
        this.nidmessenger = nidmessenger;
    }

    public int getNiddeliveryaddress() {
        return niddeliveryaddress;
    }

    public void setNiddeliveryaddress(int niddeliveryaddress) {
        this.niddeliveryaddress = niddeliveryaddress;
    }

    public Short getDeta() {
        return deta;
    }

    public void setDeta(Short deta) {
        this.deta = deta;
    }

    public boolean isBinvoiced() {
        return binvoiced;
    }

    public void setBinvoiced(boolean binvoiced) {
        this.binvoiced = binvoiced;
    }

    public boolean isBisterminalpayment() {
        return bisterminalpayment;
    }

    public void setBisterminalpayment(boolean bisterminalpayment) {
        this.bisterminalpayment = bisterminalpayment;
    }

    public Date getDlastupdate() {
        return dlastupdate;
    }

    public void setDlastupdate(Date dlastupdate) {
        this.dlastupdate = dlastupdate;
    }

    public Integer getNidstatus() {
        return nidstatus;
    }

    public void setNidstatus(Integer nidstatus) {
        this.nidstatus = nidstatus;
    }

    public String getSinvoiceid() {
        return sinvoiceid;
    }

    public void setSinvoiceid(String sinvoiceid) {
        this.sinvoiceid = sinvoiceid;
    }

    public Set<Purchaseorderdetail> getPurchaseorderdetails() {
        return purchaseorderdetails;
    }

    public void setPurchaseorderdetails(Set<Purchaseorderdetail> purchaseorderdetails) {
        this.purchaseorderdetails = purchaseorderdetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchaseorder)) return false;

        Purchaseorder that = (Purchaseorder) o;

        if (getNidclient() != that.getNidclient()) return false;
        if (getNidmessenger() != that.getNidmessenger()) return false;
        if (getNiddeliveryaddress() != that.getNiddeliveryaddress()) return false;
        if (isBinvoiced() != that.isBinvoiced()) return false;
        if (isBisterminalpayment() != that.isBisterminalpayment()) return false;
        if (getNidpurchaseorder() != null ? !getNidpurchaseorder().equals(that.getNidpurchaseorder()) : that.getNidpurchaseorder() != null)
            return false;
        if (getCatPaymenttype() != null ? !getCatPaymenttype().equals(that.getCatPaymenttype()) : that.getCatPaymenttype() != null)
            return false;
        if (getDeta() != null ? !getDeta().equals(that.getDeta()) : that.getDeta() != null)
            return false;
        if (getDlastupdate() != null ? !getDlastupdate().equals(that.getDlastupdate()) : that.getDlastupdate() != null)
            return false;
        if (getNidstatus() != null ? !getNidstatus().equals(that.getNidstatus()) : that.getNidstatus() != null)
            return false;
        if (getSinvoiceid() != null ? !getSinvoiceid().equals(that.getSinvoiceid()) : that.getSinvoiceid() != null)
            return false;
        return !(getPurchaseorderdetails() != null ? !getPurchaseorderdetails().equals(that.getPurchaseorderdetails()) : that.getPurchaseorderdetails() != null);

    }

    @Override
    public int hashCode() {
        int result = getNidpurchaseorder() != null ? getNidpurchaseorder().hashCode() : 0;
        result = 31 * result + (getCatPaymenttype() != null ? getCatPaymenttype().hashCode() : 0);
        result = 31 * result + getNidclient();
        result = 31 * result + getNidmessenger();
        result = 31 * result + getNiddeliveryaddress();
        result = 31 * result + (getDeta() != null ? getDeta().hashCode() : 0);
        result = 31 * result + (isBinvoiced() ? 1 : 0);
        result = 31 * result + (isBisterminalpayment() ? 1 : 0);
        result = 31 * result + (getDlastupdate() != null ? getDlastupdate().hashCode() : 0);
        result = 31 * result + (getNidstatus() != null ? getNidstatus().hashCode() : 0);
        result = 31 * result + (getSinvoiceid() != null ? getSinvoiceid().hashCode() : 0);
        result = 31 * result + (getPurchaseorderdetails() != null ? getPurchaseorderdetails().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Purchaseorder{" +
                "nidpurchaseorder=" + nidpurchaseorder +
                ", catPaymenttype=" + catPaymenttype +
                ", nidclient=" + nidclient +
                ", nidmessenger=" + nidmessenger +
                ", niddeliveryaddress=" + niddeliveryaddress +
                ", deta=" + deta +
                ", binvoiced=" + binvoiced +
                ", bisterminalpayment=" + bisterminalpayment +
                ", dlastupdate=" + dlastupdate +
                ", nidstatus=" + nidstatus +
                ", sinvoiceid='" + sinvoiceid + '\'' +
                ", purchaseorderdetails=" + purchaseorderdetails +
                '}';
    }
}
