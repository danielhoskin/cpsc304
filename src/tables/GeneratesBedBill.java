package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

public class GeneratesBedBill extends Has_Bill implements Table {
    private int bedid;
    private int receptionistid;

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> billAttributes = super.getAttributes();
        List<Pair<AttributeType, String>> bedBillAttributes = new ArrayList<>();
        List<Pair<AttributeType, String>> newList = new ArrayList<>();

        bedBillAttributes.add(new Pair<>(AttributeType.INT, "bedid"));
        bedBillAttributes.add(new Pair<>(AttributeType.INT, "receptionistid"));

        newList.addAll(billAttributes);
        newList.addAll(bedBillAttributes);
        return newList;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        List<String> billPrimaryKey = super.primaryKey();
        List<String> list = new ArrayList<>();
        primarykey.add("bedid");
        primarykey.add("receptionistid");
        list.addAll(primarykey);
        list.addAll(billPrimaryKey);
        return list;
    }

    public int getBedid() {
        return bedid;
    }

    public void setBedid(int bedid) {
        this.bedid = bedid;
    }

    public int getReceptionistid() {
        return receptionistid;
    }

    public void setReceptionistid(int receptionistid) {
        this.receptionistid = receptionistid;
    }
}