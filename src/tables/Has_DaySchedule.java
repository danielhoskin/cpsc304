package tables;

import java.sql.Timestamp;

public class Has_DaySchedule implements Table {
    private int userid;
    private int dayid;
    private Timestamp timefrom;
    private Timestamp timeto;

    public Has_DaySchedule(int userid, int dayid, Timestamp timefrom, Timestamp timeto) {
        this.userid = userid;
        this.dayid = dayid;
        this.timefrom = timefrom;
        this.timeto = timeto;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getDayid() {
        return dayid;
    }

    public void setDayid(int dayid) {
        this.dayid = dayid;
    }

    public Timestamp getTimefrom() {
        return timefrom;
    }

    public void setTimefrom(Timestamp timefrom) {
        this.timefrom = timefrom;
    }

    public Timestamp getTimeto() {
        return timeto;
    }

    public void setTimeto(Timestamp timeto) {
        this.timeto = timeto;
    }
}
