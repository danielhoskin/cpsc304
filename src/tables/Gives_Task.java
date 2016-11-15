package tables;

import main.AttributeType;
	import main.Pair;

	import java.util.ArrayList;
	import java.util.List;
	
public class Gives_Task implements Table{

	private int doctorid;
	private int nurseid;
	private String notes;

    public Gives_Task(int doctorid, int nurseid, String notes) {
        this.doctorid = doctorid;
        this.nurseid = nurseid;
        this.notes = notes;
    }

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> givestaskAttributes = new ArrayList<>();
        givestaskAttributes.add(new Pair<>(AttributeType.INT, "doctorid"));
        givestaskAttributes.add(new Pair<>(AttributeType.INT, "nurseid"));
        givestaskAttributes.add(new Pair<>(AttributeType.STRING, "notes"));
        return givestaskAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("doctorid");
        primarykey.add("nurseid");
        return primarykey;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
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
