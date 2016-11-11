package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Has_Note implements Table {
    private int activityid;
    private int patientid;
    private int doctorid;
    private int diagnosisid;
    private int noteid;
    private String body;

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> noteAttributes = new ArrayList<>();
        noteAttributes.add(new Pair<>(AttributeType.INT, "activityid"));
        noteAttributes.add(new Pair<>(AttributeType.INT, "patientid"));
        noteAttributes.add(new Pair<>(AttributeType.INT, "doctorid"));
        noteAttributes.add(new Pair<>(AttributeType.INT, "diagnosisid"));
        noteAttributes.add(new Pair<>(AttributeType.INT, "noteid"));
        noteAttributes.add(new Pair<>(AttributeType.STRING, "body"));
        return noteAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("activityid");
        primarykey.add("patientid");
        primarykey.add("doctorid");
        primarykey.add("diagnosisid");
        primarykey.add("noteid");
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

    public int getDiagnosisid() {
        return diagnosisid;
    }

    public void setDiagnosisid(int diagnosisid) {
        this.diagnosisid = diagnosisid;
    }

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
