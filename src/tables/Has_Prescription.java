package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Has_Prescription implements Table{
    private int activityid;
    private int patientid;
    private int doctorid;
    private int diagnosisid;
    private int prescriptionid;
    private String details;

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> prescriptionAttributes = new ArrayList<>();
        prescriptionAttributes.add(new Pair<>(AttributeType.INT, "activityid"));
        prescriptionAttributes.add(new Pair<>(AttributeType.INT, "patientid"));
        prescriptionAttributes.add(new Pair<>(AttributeType.INT, "doctorid"));
        prescriptionAttributes.add(new Pair<>(AttributeType.INT, "diagnosisid"));
        prescriptionAttributes.add(new Pair<>(AttributeType.INT, "prescriptionid"));
        prescriptionAttributes.add(new Pair<>(AttributeType.STRING, "details"));
        return prescriptionAttributes;
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

    public int getPrescriptionid() {
        return prescriptionid;
    }

    public void setPrescriptionid(int prescriptionid) {
        this.prescriptionid = prescriptionid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("activityid");
        primarykey.add("patientid");
        primarykey.add("doctorid");
        primarykey.add("diagnosisid");
        primarykey.add("prescriptionid");
        return primarykey;
    }
}
