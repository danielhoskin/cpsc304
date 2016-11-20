package tables;

public class Receptionist extends Users implements Table {
    private int receptionistid;

    public Receptionist(String username, String password, String name, String phonenumber, int receptionistid) {
        super(receptionistid, username, password, name, phonenumber);
        this.receptionistid = receptionistid;
    }

    public int getReceptionistid() { return receptionistid;
    }

    public void setReceptionistid(int receptionistid) {
        this.receptionistid = receptionistid;
    }
}
