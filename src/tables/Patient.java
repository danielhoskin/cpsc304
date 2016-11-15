package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends Users implements Table {
    private int patientid;
    private Date dateofbirth;
    private String sex;
    private String medicalhistory;

    public Patient( String username, String password, String name, String phonenumber, int patientid, Date dateofbirth, String sex, String medicalhistory) {
        super(patientid, username, password, name, phonenumber);
        this.patientid = patientid;
        this.dateofbirth = dateofbirth;
        this.sex = sex;
        this.medicalhistory = medicalhistory;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("patientid");
        return primarykey;
    }

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> userAttributes = super.getAttributes();
        userAttributes.remove(new Pair<>(AttributeType.INT, "userid"));
        List<Pair<AttributeType, String>> patientAttributes = new ArrayList<>();
        List<Pair<AttributeType, String>> newList = new ArrayList<>(userAttributes.size() + patientAttributes.size());
        patientAttributes.add(new Pair<>(AttributeType.DATE, "dateofbirth"));
        patientAttributes.add(new Pair<>(AttributeType.STRING, "sex"));
        patientAttributes.add(new Pair<>(AttributeType.STRING, "medicalhistory"));
        patientAttributes.add(new Pair<>(AttributeType.INT, "patientid"));
        newList.addAll(userAttributes);
        newList.addAll(patientAttributes);
        return newList;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public String getMedicalhistory() {
        return medicalhistory;
    }

    public void setMedicalhistory(String medicalhistory) {
        this.medicalhistory = medicalhistory;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMedicalHistory() {
        return medicalhistory;
    }

    public void setMedicalistory(String medicalHistory) {
        this.medicalhistory = medicalHistory;
    }
}
