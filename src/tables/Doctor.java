package tables;

public class Doctor extends Users implements Table {
    private int doctorid;

    public Doctor(String username, String password, String name, String phonenumber, int doctorid) {
        super(doctorid, username, password, name, phonenumber);
        this.doctorid = doctorid;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }
}
