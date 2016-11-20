package tables;

import java.sql.Timestamp;

public class GeneratesBedBill extends Has_Bill implements Table {
    private int bedid;
    private int receptionistid;

    public GeneratesBedBill(int patientid, int billid, float amountdue, float amountpaid, Timestamp day, int bedid, int receptionistid) {
        super(patientid, billid, amountdue, amountpaid, day);
        this.bedid = bedid;
        this.receptionistid = receptionistid;
    }

    public int getBedid() {
        return bedid;
    }

    public void setBedid(int bedid) {
        this.bedid = bedid;
    }

    public int getReceptionistid() {
        return receptionistid;
    }

    public void setReceptionistid(int receptionistid) {
        this.receptionistid = receptionistid;
    }
}