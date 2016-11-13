package database;

import main.Pair;
import tables.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance = null;
    private Connection connection;

    private Database() {
        try {
            String connectionURL = "jdbc:oracle:thin:@localhost:1522:ug";
            String username = "ora_h8y9a";
            String password = "a37594132";
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = DriverManager.getConnection(connectionURL, username, password);
        }
        catch (SQLException e) {
            System.out.println("Message: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static Database getInstance(){
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public boolean checkUser(int userid, String password) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from users";
        ResultSet rs = stmt.executeQuery(query);
        int uid;
        String pw;

        while (rs.next()) {
            uid = rs.getInt("userid");
            pw = rs.getString("password").trim();

            if (userid == uid && password.equals(pw)) {
                return true;
            }
        }
        return false;
    }

    /* You must specify age and sex. For all ages, pass in age == -1. For both sexes, "mf". */
    public List<Patient> viewPatients(int age, String sex, String medicalHistory) throws SQLException {
        Statement stmt = connection.createStatement();
        String select = "select * ";
        String from = "from patient p, users u ";
        String where;
        if (age == -1 && sex.toLowerCase().equals("mf")) {
           where = "where p.patientid = u.userid and medicalhistory like '%" + medicalHistory + "%'";
        } else {
            int dob = Year.now().getValue() - age;
            where = "where p.patientid = u.userid and extract(year from dateofbirth) = " + dob + " and sex = '" + sex.toLowerCase() + "' and medicalhistory like '%" + medicalHistory + "%'";
        }
        String query;

        query = select + from + where;
        ResultSet rs = stmt.executeQuery(query);

        int pi;
        String un;
        String pw;
        String pn;
        String phn;
        Date db;
        String sx;
        String mh;

        List<Patient> patients = new ArrayList<>();

        while (rs.next()) {
            pi = rs.getInt("patientid");
            un = rs.getString("username");
            pw = rs.getString("password");
            pn = rs.getString("name");
            Reader r = rs.getCharacterStream("phonenumber");
            BufferedReader br = new BufferedReader(r);
            StringBuilder rlst = new StringBuilder();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    rlst.append(line);
                }
                phn = rlst.toString();
            } catch (IOException e) {
                throw new SQLException();
            }
            db = rs.getDate("dateofbirth");
            r = rs.getCharacterStream("sex");
            br = new BufferedReader(r);
            rlst = new StringBuilder();

            try {
                while ((line = br.readLine()) != null) {
                    rlst.append(line);
                }
                sx = rlst.toString();
            } catch (IOException e) {
                throw new SQLException();
            }
            mh = rs.getString("medicalhistory");
            patients.add(new Patient(un, pw, pn, phn, pi, db, sx, mh));
        }
        return patients;
    }

    public Pair<Integer, String> findBedLocation(int patientid) throws SQLException{
        Statement stmt = connection.createStatement();
        String query = "select b.bedid, b.location from bed b, assignedto at where b.bedid = at.bedid and at.patientid = " + patientid;
        ResultSet rs = stmt.executeQuery(query);

        Integer bedid = null;
        String location = "";

        while (rs.next()) {
            bedid = rs.getInt("bedid");
            Reader r = rs.getCharacterStream("location");
            BufferedReader br = new BufferedReader(r);
            StringBuilder rlst = new StringBuilder();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    rlst.append(line);
                }
                location = rlst.toString();
            } catch (IOException e) {
                throw new SQLException();
            }
        }
        return new Pair<>(bedid, location);
    }

    public List<Integer> monitoredByEveryNurse() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select p.patientid from patient p where not exists (" +
                "(select nurseid from nurse) minus " +
                "(select m.nurseid from monitors m where m.patientid = p.patientid))";
        ResultSet rs = stmt.executeQuery(query);

        List<Integer> patients = new ArrayList<>();
        Integer pid;
        while (rs.next()) {
            pid = rs.getInt("patientid");
            patients.add(pid);
        }
        return patients;
    }

    public boolean addMonitors(int patientid, int nurseid, String notes) throws SQLException {
        Statement stmt = connection.createStatement();
        String query;
        if (notes.isEmpty()) {
           query =  "insert into monitors values(" + patientid + ", " + nurseid + ", null)";
        } else {
           query = "insert into monitors values(" + patientid + ", " + nurseid + ", '" + notes + "')";
        }
        int result = stmt.executeUpdate(query);
        return result == 1;
    }

    public List<Monitors> getMonitors(int nurseid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from monitors m where m.nurseid = " + nurseid;
        ResultSet rs = stmt.executeQuery(query);

        List<Monitors> monitors = new ArrayList<>();
        int pid;
        String notes;

        while (rs.next()) {
            pid = rs.getInt("patientid");
            notes = rs.getString("notes");
            monitors.add(new Monitors(pid, nurseid, notes));
        }
        return monitors;
    }

    public List<Pair<Integer, Float>> paidTheHighestBill() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select b.patientid, amountdue from has_bill b where b.amountdue >= (select max(amountdue) from has_bill)";
        ResultSet rs = stmt.executeQuery(query);

        List<Pair<Integer, Float>> patients = new ArrayList<>();
        Integer pid;
        Float ad;
        while (rs.next()) {
            pid = rs.getInt("patientid");
            ad = rs.getFloat("amountdue");
            patients.add(new Pair<>(pid, ad));
        }
        return patients;
    }

    public List<Pair<Integer, Float>> paidTheLowestBill() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select b.patientid, b.amountdue from has_bill b where b.amountdue <= (select min(amountdue) from has_bill)";
        ResultSet rs = stmt.executeQuery(query);

        List<Pair<Integer, Float>> patients = new ArrayList<>();
        Integer pid;
        Float ad;
        while (rs.next()) {
            pid = rs.getInt("patientid");
            ad = rs.getFloat("amountdue");
            patients.add(new Pair<>(pid, ad));
        }
        return patients;
    }

    public boolean deleteBed(int bedid) throws SQLException {
        Statement stmt = connection.createStatement();
        Statement check = connection.createStatement();
        String cond = "select bedid from assignedto where bedid = " + bedid;
        ResultSet rs = check.executeQuery(cond);

        if (rs.next()) {
            return false;
        } else {
            String query = "delete from bed where bedid = " + bedid;
            int result = stmt.executeUpdate(query);
            return result == 1;
        }
    }

    public List<Patient> getPatients() throws  SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from patient, users where patient.patientid = users.userid";
        ResultSet rs = stmt.executeQuery(query);

        int pi;
        String un;
        String pw;
        String pn;
        String phn;
        Date db;
        String sx;
        String mh;

        List<Patient> patients = new ArrayList<>();

        while (rs.next()) {
            pi = rs.getInt("patientid");
            un = rs.getString("username");
            pw = rs.getString("password");
            pn = rs.getString("name");
            Reader r = rs.getCharacterStream("phonenumber");
            BufferedReader br = new BufferedReader(r);
            StringBuilder rlst = new StringBuilder();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    rlst.append(line);
                }
                phn = rlst.toString();
            } catch (IOException e) {
                throw new SQLException();
            }
            db = rs.getDate("dateofbirth");
            r = rs.getCharacterStream("sex");
            br = new BufferedReader(r);
            rlst = new StringBuilder();

            try {
                while ((line = br.readLine()) != null) {
                    rlst.append(line);
                }
                sx = rlst.toString();
            } catch (IOException e) {
                throw new SQLException();
            }
            mh = rs.getString("medicalhistory");
            patients.add(new Patient(un, pw, pn, phn, pi, db, sx, mh));
        }
        return patients;
    }

    public List<Doctor> getDoctors() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from doctor, users where doctorid = userid";
        ResultSet rs = stmt.executeQuery(query);

        int di;
        String un;
        String pw;
        String dn;
        String phn;

        List<Doctor> doctors = new ArrayList<>();

        while (rs.next()) {
            di = rs.getInt("doctorid");
            un = rs.getString("username");
            pw = rs.getString("password");
            dn = rs.getString("name");
            Reader r = rs.getCharacterStream("phonenumber");
            BufferedReader br = new BufferedReader(r);
            StringBuilder rlst = new StringBuilder();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    rlst.append(line);
                }
                phn = rlst.toString();
            } catch (IOException e) {
                throw new SQLException();
            }
            doctors.add(new Doctor(un, pw, dn, phn, di));
        }
        return doctors;
    }

    public List<Nurse> getNurses() throws  SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from nurse, users where nurseid = userid";
        ResultSet rs = stmt.executeQuery(query);

        int ni;
        String un;
        String pw;
        String nn;
        String phn;

        List<Nurse> nurses = new ArrayList<>();

        while (rs.next()) {
            ni = rs.getInt("nurseid");
            un = rs.getString("username");
            pw = rs.getString("password");
            nn = rs.getString("name");
            Reader r = rs.getCharacterStream("phonenumber");
            BufferedReader br = new BufferedReader(r);
            StringBuilder rlst = new StringBuilder();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    rlst.append(line);
                }
                phn = rlst.toString();
            } catch (IOException e) {
                throw new SQLException();
            }
            nurses.add(new Nurse(un, pw, nn, phn, ni));
        }
        return nurses;
    }

    public List<Receptionist> getReceptionists() throws  SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from receptionist, users where receptionistid = userid";
        ResultSet rs = stmt.executeQuery(query);

        int ri;
        String un;
        String pw;
        String nn;
        String phn;

        List<Receptionist> receptionists = new ArrayList<>();

        while (rs.next()) {
            ri = rs.getInt("receptionistid");
            un = rs.getString("username");
            pw = rs.getString("password");
            nn = rs.getString("name");
            Reader r = rs.getCharacterStream("phonenumber");
            BufferedReader br = new BufferedReader(r);
            StringBuilder rlst = new StringBuilder();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    rlst.append(line);
                }
                phn = rlst.toString();
            } catch (IOException e) {
                throw new SQLException();
            }
            receptionists.add(new Receptionist(un, pw, nn, phn, ri));
        }
        return receptionists;
    }

    public List<Has_Diagnosis> getDiagnosises(int doctorid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from has_diagnosis d where d.doctorid = " + doctorid;
        ResultSet rs = stmt.executeQuery(query);

        int aid;
        int pid;
        int did;

        List<Has_Diagnosis> diagnosises = new ArrayList<>();

        while (rs.next()) {
            aid = rs.getInt("activityid");
            pid = rs.getInt("patientid");
            did = rs.getInt("diagnosisid");
            diagnosises.add(new Has_Diagnosis(aid, pid, doctorid, did));
        }
        return diagnosises;
    }

   public boolean isPatient(int userid) throws SQLException {
       Statement stmt = connection.createStatement();
       String query = "select patientid from patient";
       ResultSet rs = stmt.executeQuery(query);

       int pid;
       while (rs.next()) {
           pid = rs.getInt("patientid");
           if (pid == userid) {
               return true;
           }
       }
       return false;
   }

    public boolean isDoctor(int userid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select doctorid from doctor";
        ResultSet rs = stmt.executeQuery(query);

        int did;
        while (rs.next()) {
            did = rs.getInt("doctorid");
            if (did == userid) {
                return true;
            }
        }
        return false;
    }

    public boolean isNurse(int userid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select nurseid from nurse";
        ResultSet rs = stmt.executeQuery(query);

        int nid;
        while (rs.next()) {
            nid = rs.getInt("nurseid");
            if (nid == userid) {
                return true;
            }
        }
        return false;
    }

    public boolean isReceptionist(int userid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select receptionistid from receptionist";
        ResultSet rs = stmt.executeQuery(query);

        int rid;
        while (rs.next()) {
            rid = rs.getInt("receptionistid");
            if (rid == userid) {
                return true;
            }
        }
        return false;
    }

    // TODO:
    public boolean deletePatient(int patientid) throws SQLException {
        return false;
    }

    // TODO:
    public boolean addActivity(int patientid, int doctorid, int nurseid, Timestamp starttime, Timestamp endtime) throws SQLException {
        return false;
    }

    public List<Has_DaySchedule> getDaySchedule(int userId) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from has_dayschedule where userid = " + userId;
        ResultSet rs = stmt.executeQuery(query);

        List<Has_DaySchedule> schedules = new ArrayList<>();
        Date dt;
        String af;
        String at;

        while (rs.next()) {
            dt = rs.getDate("day");
            af = rs.getString("availablefrom");
            at = rs.getString("availableto");
            schedules.add(new Has_DaySchedule(userId, dt, af, at));
        }
        return schedules;
    }

    public List<HasActivty> getPatientActivities(int patientId) throws  SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from has_activity where patientid = " + patientId;
        ResultSet rs = stmt.executeQuery(query);

        List<HasActivty> activities = new ArrayList<>();
        int aid;
        int did;
        int nid;
        String st;
        String et;

        while (rs.next()) {
            aid = rs.getInt("activityid");
            did = rs.getInt("doctorid");
            nid = rs.getInt("nurseid");
            st = rs.getString("starttime");
            et = rs.getString("endtime");
            activities.add(new HasActivty(aid, patientId, did, nid, st, et));
        }
        return activities;
    }

    public static void main(String[] args) {
        Database db = Database.getInstance();
        try {
            List<Patient> patients = db.viewPatients(26, "m", "filler");
            patients = db.viewPatients(-1, "mf", "");

            Pair<Integer, String> pair = db.findBedLocation(43219832);

            List<Pair<Integer, Float>> p = db.paidTheHighestBill();
            List<Pair<Integer, Float>> p2 = db.paidTheLowestBill();

            //System.out.println(db.deleteBed(10));

            List<Integer> ps = db.monitoredByEveryNurse();
            for(Integer i : ps) {
                //System.out.println(i);
            }

            //db.addMonitors(43219832, 18392058, "hello");
            db.getDaySchedule(43219832);
            db.getPatientActivities(43219832);
            for (Patient pat : db.getPatients()) {
                //System.out.println(pat.getPatientid());
            }
            for (Doctor doc: db.getDoctors()) {
                //System.out.println(doc.getDoctorid());
            }
            for (Nurse nu: db.getNurses()) {
                //System.out.println(nu.getNurseid());
            }
            for (Receptionist rep: db.getReceptionists()) {
                //System.out.println(rep.getReceptionistid());
            }
            db.getMonitors(12938183);
            db.getDiagnosises(18392183);
        } catch (SQLException e) {
            System.out.println("Message: " + e.getMessage());
            System.exit(-1);
        }
    }
}
