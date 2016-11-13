package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Operation extends HasActivty implements Table{
    private String type;
    private double cost;

    public Operation(int activityid, int patientid, int doctorid, int nurseid, String starttime, String endtime, String type, double cost) {
        super(activityid, patientid, doctorid, nurseid, starttime, endtime);
        this.type = type;
        this.cost = cost;
    }

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> userAttributes = super.getAttributes();
        List<Pair<AttributeType, String>> patientAttributes = new ArrayList<>();
        List<Pair<AttributeType, String>> newList = new ArrayList<>(userAttributes.size() + patientAttributes.size());
        patientAttributes.add(new Pair<>(AttributeType.STRING, "type"));
        patientAttributes.add(new Pair<>(AttributeType.STRING, "cost"));
        newList.addAll(userAttributes);
        newList.addAll(patientAttributes);
        return newList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
