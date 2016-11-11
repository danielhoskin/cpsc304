package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Bed implements Table{
    private int bedid;
    private String location;

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> bedAttributes = new ArrayList<>();
        bedAttributes.add(new Pair<>(AttributeType.STRING, "bedid"));
        bedAttributes.add(new Pair<>(AttributeType.STRING, "location"));
        return bedAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("bedid");
        primarykey.add("location");
        return primarykey;
    }

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
