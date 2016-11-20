package tables;


import java.sql.Timestamp;

public class GeneratesOperationBill extends Has_Bill implements Table {
    private int activityid;
    private int doctorid;
    private int receptionistid;

    public GeneratesOperationBill(int patientid, int billid, float amountdue, float amountpaid, Timestamp day, int activityid, int doctorid, int receptionistid) {
        super(patientid, billid, amountdue, amountpaid, day);
        this.activityid = activityid;
        this.doctorid = doctorid;
        this.receptionistid = receptionistid;
    }

    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public int getReceptionistid() {
        return receptionistid;
    }

    public void setReceptionistid(int receptionistid) {
        this.receptionistid = receptionistid;
    }
}
