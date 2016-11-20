package tables;

import java.sql.Timestamp;

public class Has_Bill implements Table {
    private int patientid;
    private int billid;
    private float amountdue;
    private float amountpaid;
    private Timestamp day;

    public Has_Bill(int patientid, int billid, float amountdue, float amountpaid, Timestamp day) {
        this.patientid = patientid;
        this.billid = billid;
        this.amountdue = amountdue;
        this.amountpaid = amountpaid;
        this.day = day;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public double getAmountdue() {
        return amountdue;
    }

    public void setAmountdue(float amountdue) {
        this.amountdue = amountdue;
    }

    public double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(float amountpaid) {
        this.amountpaid = amountpaid;
    }

    public Timestamp getDay() {
        return day;
    }

    public void setDay(Timestamp day) {
        this.day = day;
    }
}
