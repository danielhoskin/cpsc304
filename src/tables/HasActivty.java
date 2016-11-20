package tables;

import main.AttributeType;
import main.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HasActivty implements Table {
    private int activityid;
    private int patientid;
    private int doctorid;
    private int nurseid;
    private String starttime;
    private String endtime;

    public HasActivty(int activityid, int patientid, int doctorid, int nurseid, String starttime, String endtime) {
        this.activityid = activityid;
        this.patientid = patientid;
        this.doctorid = doctorid;
        this.nurseid = nurseid;
        this.starttime = starttime;
        this.endtime = endtime;
    }
    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> hasactivityAttributes = new ArrayList<>();
        hasactivityAttributes.add(new Pair<AttributeType, String>(AttributeType.INT, "activityid"));
        hasactivityAttributes.add(new Pair<AttributeType, String>(AttributeType.INT, "patientid"));
        hasactivityAttributes.add(new Pair<AttributeType, String>(AttributeType.INT, "doctorid"));
        hasactivityAttributes.add(new Pair<AttributeType, String>(AttributeType.INT, "nurseid"));
        hasactivityAttributes.add(new Pair<AttributeType, String>(AttributeType.TIMESTAMP, "starttime"));
        hasactivityAttributes.add(new Pair<AttributeType, String>(AttributeType.TIMESTAMP, "endtime"));
        return hasactivityAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("activityid");
        primarykey.add("patientid");
        primarykey.add("doctorid");
        return primarykey;
    }

    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public int getNurseid() {
        return nurseid;
    }

    public void setNurseid(int nurseid) {
        this.nurseid = nurseid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
