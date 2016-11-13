package tables;

import main.AttributeType;
import main.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GeneratesOperationBill extends Has_Bill implements Table {
    private int activityid;
    private int doctorid;
    private int receptionistid;

    public GeneratesOperationBill(int patientid, int billid, float amountdue, float amountpaid, Timestamp day, int activityid, int doctorid, int receptionistid) {
        super(patientid, billid, amountdue, amountpaid, day);
        this.activityid = activityid;
        this.doctorid = doctorid;
        this.receptionistid = receptionistid;
    }

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> billAttributes = super.getAttributes();
        List<Pair<AttributeType, String>> operationBillAttributes = new ArrayList<>();
        List<Pair<AttributeType, String>> newList = new ArrayList<>();

        operationBillAttributes.add(new Pair<>(AttributeType.INT, "activityid"));
        operationBillAttributes.add(new Pair<>(AttributeType.INT, "doctorid"));
        operationBillAttributes.add(new Pair<>(AttributeType.INT, "receptionistid"));

        newList.addAll(billAttributes);
        newList.addAll(operationBillAttributes);
        return newList;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        List<String> billPrimaryKey = super.primaryKey();
        List<String> list = new ArrayList<>();
        primarykey.add("activityid");
        primarykey.add("doctorid");
        primarykey.add("receptionistid");
        list.addAll(primarykey);
        list.addAll(billPrimaryKey);
        return list;
    }

    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public int getReceptionistid() {
        return receptionistid;
    }

    public void setReceptionistid(int receptionistid) {
        this.receptionistid = receptionistid;
    }
}
