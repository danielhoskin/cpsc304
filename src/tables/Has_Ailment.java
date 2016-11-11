package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Has_Ailment implements Table{
    private int activityid;
    private int patientid;
    private int doctorid;
    private int diagnosisid;
    private int ailmentid;
    private String details;

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> ailmentAttributes = new ArrayList<>();
        ailmentAttributes.add(new Pair<>(AttributeType.INT, "activityid"));
        ailmentAttributes.add(new Pair<>(AttributeType.INT, "patientid"));
        ailmentAttributes.add(new Pair<>(AttributeType.INT, "doctorid"));
        ailmentAttributes.add(new Pair<>(AttributeType.INT, "diagnosisid"));
        ailmentAttributes.add(new Pair<>(AttributeType.INT, "ailmentid"));
        ailmentAttributes.add(new Pair<>(AttributeType.STRING, "details"));
        return ailmentAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("activityid");
        primarykey.add("doctorid");
        primarykey.add("patientid");
        primarykey.add("diagnosisid");
        primarykey.add("ailmentid");
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

    public int getAilmentid() {
        return ailmentid;
    }

    public void setAilmentid(int ailmentid) {
        this.ailmentid = ailmentid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
