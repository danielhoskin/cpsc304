package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Appointment extends HasActivty implements Table{
    private String notes;

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> userAttributes = super.getAttributes();
        List<Pair<AttributeType, String>> patientAttributes = new ArrayList<>();
        List<Pair<AttributeType, String>> newList = new ArrayList<>(userAttributes.size() + patientAttributes.size());
        patientAttributes.add(new Pair<>(AttributeType.STRING, "notes"));
        newList.addAll(userAttributes);
        newList.addAll(patientAttributes);
        return newList;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
