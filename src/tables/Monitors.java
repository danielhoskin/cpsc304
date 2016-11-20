package tables;

public class Monitors implements Table{
    private int patientid;
    private int nurseid;
    private String notes;

    public Monitors(int patientid, int nurseid, String notes) {
        this.patientid = patientid;
        this.nurseid = nurseid;
        this.notes = notes;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getNurseid() {
        return nurseid;
    }

    public void setNurseid(int nurseid) {
        this.nurseid = nurseid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
