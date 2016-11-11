package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends Users implements Table {
    private String patientid;
    private Date dateofbirth;
    private String sex;
    private String medicalhistory;

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("patientid");
        return primarykey;
    }

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> userAttributes = super.getAttributes();
        userAttributes.remove(new Pair<>(AttributeType.STRING, "userid"));
        List<Pair<AttributeType, String>> patientAttributes = new ArrayList<>();
        List<Pair<AttributeType, String>> newList = new ArrayList<>(userAttributes.size() + patientAttributes.size());
        patientAttributes.add(new Pair<>(AttributeType.STRING, "dateofbirth"));
        patientAttributes.add(new Pair<>(AttributeType.STRING, "sex"));
        patientAttributes.add(new Pair<>(AttributeType.STRING, "medicalhistory"));
        patientAttributes.add(new Pair<>(AttributeType.STRING, "patientid"));
        newList.addAll(userAttributes);
        newList.addAll(patientAttributes);
        return newList;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
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
