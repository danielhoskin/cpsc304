package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Monitors implements Table{
    private int patientid;
    private int nurseid;
    private String notes;

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> monitorsAttributes = new ArrayList<>();
        monitorsAttributes.add(new Pair<>(AttributeType.STRING, "patientid"));
        monitorsAttributes.add(new Pair<>(AttributeType.STRING, "nurseid"));
        monitorsAttributes.add(new Pair<>(AttributeType.STRING, "notes"));
        return monitorsAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("patientid");
        primarykey.add("nurseid");
        return primarykey;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
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
