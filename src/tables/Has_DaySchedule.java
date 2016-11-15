package tables;

import main.AttributeType;
import main.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> dayscheduleAttributes = new ArrayList<>();
        dayscheduleAttributes.add(new Pair<>(AttributeType.INT, "userid"));
        dayscheduleAttributes.add(new Pair<>(AttributeType.INT, "dayid"));
        dayscheduleAttributes.add(new Pair<>(AttributeType.TIMESTAMP, "timefrom"));
        dayscheduleAttributes.add(new Pair<>(AttributeType.TIMESTAMP, "timeto"));
        return dayscheduleAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("userid");
        primarykey.add("dayid");
        return primarykey;
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
