package tables;


import java.sql.Timestamp;
import java.util.Date;

public class Assigned_To implements Table {
    private int patientid;
    private int bedid;
    private Timestamp admissiondate;
    private Timestamp releasedate;

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getBedid() {
        return bedid;
    }

    public void setBedid(int bedid) {
        this.bedid = bedid;
    }

    public Date getAdmissiondate() {
        return admissiondate;
    }

    public void setAdmissiondate(Timestamp admissiondate) {
        this.admissiondate = admissiondate;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Timestamp releasedate) {
        this.releasedate = releasedate;
    }
}
