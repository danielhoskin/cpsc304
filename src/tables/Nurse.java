package tables;

public class Nurse extends Users implements Table {
    private int nurseid;

    public Nurse(String username, String password, String name, String phonenumber, int nurseid) {
        super(nurseid, username, password, name, phonenumber);
        this.nurseid = nurseid;
    }

    public int getNurseid() {
        return nurseid;
    }

    public void setNurseid(int nurseid) {
        this.nurseid = nurseid;
    }
}

