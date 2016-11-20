package tables;

public class Gives_Task implements Table{

	private int doctorid;
	private int nurseid;
	private String notes;

    public Gives_Task(int doctorid, int nurseid, String notes) {
        this.doctorid = doctorid;
        this.nurseid = nurseid;
        this.notes = notes;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
