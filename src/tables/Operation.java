package tables;

public class Operation extends HasActivty implements Table{
    private String type;
    private float cost;

    public Operation(int activityid, int patientid, int doctorid, int nurseid, String starttime, String endtime, String type, float cost) {
        super(activityid, patientid, doctorid, nurseid, starttime, endtime);
        this.type = type;
        this.cost = cost;
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

    public void setCost(float cost) {
        this.cost = cost;
    }
}
