package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Has_DaySchedule implements Table {
    private int userid;
    private Date date;
    private String availablefrom;
    private String availableto;

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> dayscheduleAttributes = new ArrayList<>();
        dayscheduleAttributes.add(new Pair<>(AttributeType.INT, "userid"));
        dayscheduleAttributes.add(new Pair<>(AttributeType.DATE, "date"));
        dayscheduleAttributes.add(new Pair<>(AttributeType.STRING, "availablefrom"));
        dayscheduleAttributes.add(new Pair<>(AttributeType.STRING, "availableto"));
        return dayscheduleAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("userid");
        primarykey.add("date");
        return primarykey;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
