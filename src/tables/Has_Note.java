package tables;

public class Has_Note implements Table {
    private int activityid;
    private int patientid;
    private int doctorid;
    private int diagnosisid;
    private int noteid;
    private String body;

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
