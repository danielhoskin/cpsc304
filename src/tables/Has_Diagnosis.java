package tables;


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
