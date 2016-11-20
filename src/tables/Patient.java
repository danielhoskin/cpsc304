package tables;

import java.time.Year;
import java.util.Date;

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

    public int getAge() {
        return Year.now().getValue() - (1900 + dateofbirth.getYear());
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
