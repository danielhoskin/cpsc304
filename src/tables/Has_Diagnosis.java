package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Has_Diagnosis implements Table{
    private int activityid;
    private int patientid;
    private int doctorid;
    private int diagnosisid;

    public Has_Diagnosis(int activityid, int patientid, int doctorid, int diagnosisid) {
        this.activityid = activityid;
        this.patientid = patientid;
        this.doctorid = doctorid;
        this.diagnosisid = diagnosisid;
    }

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> diagnosisAttributes = new ArrayList<>();
        diagnosisAttributes.add(new Pair<>(AttributeType.INT, "patientid"));
        diagnosisAttributes.add(new Pair<>(AttributeType.INT, "activityid"));
        diagnosisAttributes.add(new Pair<>(AttributeType.INT, "doctorid"));
        diagnosisAttributes.add(new Pair<>(AttributeType.INT, "diagnosisid"));
        return diagnosisAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("activityid");
        primarykey.add("patientid");
        primarykey.add("doctorid");
        primarykey.add("diagnosisid");
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
}
