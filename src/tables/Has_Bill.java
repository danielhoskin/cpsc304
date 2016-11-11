package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class Has_Bill implements Table {
    private int patientid;
    private int billid;
    private double amountdue;
    private double amountpaid;
    private String day;

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public double getAmountdue() {
        return amountdue;
    }

    public void setAmountdue(double amountdue) {
        this.amountdue = amountdue;
    }

    public double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> billAttributes = new ArrayList<>();
        billAttributes.add(new Pair<>(AttributeType.INT, "patientid"));
        billAttributes.add(new Pair<>(AttributeType.INT, "billid"));
        billAttributes.add(new Pair<>(AttributeType.FLOAT, "amountdue"));
        billAttributes.add(new Pair<>(AttributeType.FLOAT, "amountpaid"));
        billAttributes.add(new Pair<>(AttributeType.STRING, "day"));
        return billAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("patientid");
        primarykey.add("billid");
        return primarykey;
    }
}
