package tables;

import main.AttributeType;
import main.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Assigned_To implements Table {
    private int patientid;
    private int bedid;
    private Timestamp admissiondate;
    private Timestamp releasedate;

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> assignedtoAttributes = new ArrayList<>();
        assignedtoAttributes.add(new Pair<>(AttributeType.STRING, "patientid"));
        assignedtoAttributes.add(new Pair<>(AttributeType.STRING, "bedid"));
        assignedtoAttributes.add(new Pair<>(AttributeType.TIMESTAMP, "admissiondate"));
        assignedtoAttributes.add(new Pair<>(AttributeType.TIMESTAMP, "releasedate"));
        return assignedtoAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("patientid");
        primarykey.add("bedid");
        return primarykey;
    }

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
