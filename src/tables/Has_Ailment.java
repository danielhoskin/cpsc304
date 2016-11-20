package tables;

public class Has_Ailment implements Table{
    private int activityid;
    private int patientid;
    private int doctorid;
    private int diagnosisid;
    private int ailmentid;
    private String details;

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
