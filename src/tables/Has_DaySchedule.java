package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Has_DaySchedule implements Table {
    private int userid;
    private Date day;
    private String availablefrom;
    private String availableto;

    public Has_DaySchedule(int userid, Date day, String availablefrom, String availableto) {
        this.userid = userid;
        this.day = day;
        this.availablefrom = availablefrom;
        this.availableto = availableto;
    }

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> dayscheduleAttributes = new ArrayList<>();
        dayscheduleAttributes.add(new Pair<>(AttributeType.INT, "userid"));
        dayscheduleAttributes.add(new Pair<>(AttributeType.DATE, "day"));
        dayscheduleAttributes.add(new Pair<>(AttributeType.STRING, "availablefrom"));
        dayscheduleAttributes.add(new Pair<>(AttributeType.STRING, "availableto"));
        return dayscheduleAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("userid");
        primarykey.add("day");
        return primarykey;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getAvailablefrom() {
        return availablefrom;
    }

    public void setAvailablefrom(String availablefrom) {
        this.availablefrom = availablefrom;
    }

    public String getAvailableto() {
        return availableto;
    }

    public void setAvailableto(String availableto) {
        this.availableto = availableto;
    }
}
