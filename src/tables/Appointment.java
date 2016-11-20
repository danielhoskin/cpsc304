package tables;

public class Appointment extends HasActivty implements Table{
    private String notes;

    public Appointment(int activityid, int patientid, int doctorid, int nurseid, String starttime, String endtime, String notes) {
        super(activityid, patientid, doctorid, nurseid, starttime, endtime);
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
