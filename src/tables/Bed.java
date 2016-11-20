package tables;

public class Bed implements Table{
    private int bedid;
    private String location;

    public int getBedid() {
        return bedid;
    }

    public void setBedid(int bedid) {
        this.bedid = bedid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
