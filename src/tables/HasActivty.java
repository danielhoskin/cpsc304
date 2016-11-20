package tables;

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
