package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Receptionist extends Users implements Table {
    private String receptionistid;

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("receptionistid");
        return primarykey;
    }
    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> userAttributes = super.getAttributes();
        userAttributes.remove(new Pair<>(AttributeType.STRING, "userid"));
        List<Pair<AttributeType, String>> patientAttributes = new ArrayList<>();
        List<Pair<AttributeType, String>> newList = new ArrayList<>(userAttributes.size() + patientAttributes.size());
        patientAttributes.add(new Pair<>(AttributeType.STRING, "receptionistid"));
        newList.addAll(userAttributes);
        newList.addAll(patientAttributes);
        return newList;
    }

    public String getReceptionistid() { return receptionistid;
    }

    public void setReceptionistid(String receptionistid) {
        this.receptionistid = receptionistid;
    }
}
