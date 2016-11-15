package database;

import main.Pair;
import tables.*;

import javax.print.Doc;

import exceptions.ActivityException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance = null;
    private Connection connection;

    private Database() {
        try {
            String connectionURL = "jdbc:oracle:thin:@localhost:1522:ug";
            String username = "ora_y9w8";
            String password = "a35170133";
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
            System.out.println(un + " " + pw + " " + pn + " " + phn + " " + pi + " " + db + " " + sx + " " + mh);
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
        System.out.println( bedid + " " + location );
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
        System.out.println(query);
        int result = stmt.executeUpdate(query);
        return result == 1;
    }

    public boolean deleteMonitors(int nurseid, int patientid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "delete from monitors m where m.nurseid = " + nurseid + " and m.patientid = " + patientid;
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
            System.out.println(pid + " " + nurseid);
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
            System.out.println(pid + " " + ad);
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
            System.out.println(pid + " " + ad);
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
            pi = rs.getInt("userid");
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
            System.out.println(un + " " + pw + " " + pn + " " + phn + " " + pi + " " + db + " " + sx + " " + mh);
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
            System.out.println(aid + " " + pid + " " + doctorid + " " + did);
        }
        return diagnosises;
    }

    public boolean deleteDiagnosis(int diagnosisid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "delete from has_diagnosis where diagnosisid = " + diagnosisid;
        int result = stmt.executeUpdate(query);
        return result == 1;
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
    public boolean addActivity(int patientid, int doctorid, int nurseid, Timestamp starttime, Timestamp endtime) throws SQLException, ActivityException {
    	// primary key values must not be null
    	try{
    		if( starttime == null || endtime == null ) {
    			throw new ActivityException("Start and end times must be declared.");
    		}
    	} catch (ActivityException e) {
    		System.out.println("Message: " + e.getMessage());
            return false;
    	}
    	
    	// verify valid activity time frame
    	String startdate = new SimpleDateFormat("yyyy-MM-dd").format(starttime);
    	String enddate =  new SimpleDateFormat("yyyy-MM-dd").format(endtime);
    	int activityStart = Integer.parseInt(new SimpleDateFormat("H").format(starttime)) * 60 + Integer.parseInt(new SimpleDateFormat("m").format(starttime)); 
    	int activityEnd = Integer.parseInt(new SimpleDateFormat("H").format(endtime)) * 60 + Integer.parseInt(new SimpleDateFormat("m").format(endtime));
    	System.out.println( activityStart + " " + activityEnd );
    	System.out.println( startdate + " " + enddate);
    	try {
    		if( !(startdate.equals(enddate)) ){
    			throw new ActivityException("Cannot have an activity spanning more than 1 day.");
    		}
    		if( activityStart >= activityEnd ){
    			throw new ActivityException("Cannot have an activity with invalid duration.");
    		}
        } catch (ActivityException e) {
            System.out.println("Message: " + e.getMessage());
            return false;
        }
    	
    	boolean patientCanAdd = false, doctorCanAdd = false, nurseCanAdd = false;
    	
    	patientCanAdd = this.instance.canAddActivity( "Patient", patientid, starttime, endtime, startdate, activityStart, activityEnd );
        doctorCanAdd = this.instance.canAddActivity( "Doctor", doctorid, starttime, endtime, startdate, activityStart, activityEnd );
    	
        // if nurse not required for activity, set to true
    	if( nurseid == 0 ){
    		nurseCanAdd = true;
    	} else {
    		nurseCanAdd = this.instance.canAddActivity( "Nurse", nurseid, starttime, endtime, startdate, activityStart, activityEnd );
    	}
        
        if( patientCanAdd && doctorCanAdd && nurseCanAdd ){
        	Statement stmt = connection.createStatement();
        	String query;
        	if( nurseid == 0 )
        		query = "insert into has_activity values(seq_activityid.nextval, " + patientid + "," + doctorid + ", null, '" + starttime + "','" + endtime + "')";
        	else
        		query = "insert into has_activity values(seq_activityid.nextval, " + patientid + "," + doctorid + "," + nurseid + ",'" + starttime + "','" + endtime + "')";
        	return stmt.executeUpdate(query) == 1;
        }
    	return false;
    }
    
    public boolean canAddActivity( String usertype, int id, Timestamp starttime, Timestamp endtime, String date, int activityStart, int activityEnd ) throws SQLException, ActivityException {
    	Statement stmt = connection.createStatement();
        String query;
        
        // see if there are any special schedules
        String startdayofweeknum = new SimpleDateFormat("u").format(starttime);	// returns "1" to "7", 1 = Monday, 2 = Tuesday...
        System.out.println(startdayofweeknum);
        int scheduleStart = 540; // 9 am in minutes
        int scheduleEnd = 1020;	// 5 pm in minutes
        int dayid = 0;
        
        // Assumes only 1 has_dayschedule entry per day
        query = "select dayid, timefrom, timeto from has_dayschedule where userid = " + id + " and to_char( timefrom, 'yyyy-mm-dd' ) = '" + date + "'";
        ResultSet rs = stmt.executeQuery(query);

        while( rs.next() ){
        	dayid = rs.getInt("dayid");
        	scheduleStart = Integer.parseInt(new SimpleDateFormat("H").format(rs.getTimestamp("timefrom"))) * 60 +
							Integer.parseInt(new SimpleDateFormat("m").format(rs.getTimestamp("timefrom")));
        	scheduleEnd = 	Integer.parseInt(new SimpleDateFormat("H").format(rs.getTimestamp("timeto"))) * 60 +
							Integer.parseInt(new SimpleDateFormat("m").format(rs.getTimestamp("timeto")));
        }

        // if no special schedules, use days of week and if day of week schedule is not specified, default schedule is 9-5
        if( dayid == 0 ) {
        	query = "select timefrom, timeto from has_dayschedule where userid = " + id + " and dayid = '" + startdayofweeknum +"'";
			rs = stmt.executeQuery(query);
			while( rs.next() ){
				scheduleStart = Integer.parseInt(new SimpleDateFormat("H").format(rs.getTimestamp("timefrom"))) * 60 +
								Integer.parseInt(new SimpleDateFormat("m").format(rs.getTimestamp("timefrom")));
				scheduleEnd = 	Integer.parseInt(new SimpleDateFormat("H").format(rs.getTimestamp("timeto"))) * 60 +
								Integer.parseInt(new SimpleDateFormat("m").format(rs.getTimestamp("timeto")));
			}
			dayid = Integer.parseInt(startdayofweeknum);
        }
    	System.out.println(dayid);
        // check if activity is within this user's schedule
        try{
        	if( scheduleStart == scheduleEnd )
        		throw new ActivityException(usertype + " is unavailable that day.");
        	else if( activityStart < scheduleStart )
        		throw new ActivityException(usertype + " is not available that early.");
        	else if( scheduleEnd < activityEnd )
        		throw new ActivityException(usertype + " is not available that late.");
        } catch (ActivityException e ){
            System.out.println("Message: " + e.getMessage());
            return false;
        }
        
        // compare against every other activity of this user in this day
        int existingActivityStart, existingActivityEnd;
        query = "select starttime, endtime from has_activity where " + usertype + "id = " + id + " and to_char(starttime, 'yyyy-mm-dd') = '" + date +"'";
        rs = stmt.executeQuery(query);
        while( rs.next() ){
        	existingActivityStart = Integer.parseInt(new SimpleDateFormat("H").format(rs.getTimestamp("starttime"))) * 60 +
        							Integer.parseInt(new SimpleDateFormat("m").format(rs.getTimestamp("starttime")));
        	existingActivityEnd = 	Integer.parseInt(new SimpleDateFormat("H").format(rs.getTimestamp("endtime"))) * 60 +
									Integer.parseInt(new SimpleDateFormat("m").format(rs.getTimestamp("endtime")));
        	try{
        		if( activityStart >= existingActivityStart && activityStart < existingActivityEnd )
        			throw new ActivityException(usertype+"AddActivity conflict: this activity begins during another activity.");
        		if( activityEnd > existingActivityStart && activityEnd <= existingActivityEnd )
        			throw new ActivityException(usertype+"AddActivity conflict: this activity ends during another activity.");
        		if( existingActivityStart >= activityStart && existingActivityStart < activityEnd )
        			throw new ActivityException(usertype+"AddActivity conflict: another activity begins during your activity.");
        		if( existingActivityEnd > activityStart && existingActivityEnd <= activityEnd )
        			throw new ActivityException(usertype+"AddActivity conflict: anoterh activity ends during your activity.");
        	} catch (ActivityException e){
                System.out.println("Message: " + e.getMessage());
                return false;
        	}
        }
        return true;
    }

    public List<Has_DaySchedule> getDaySchedule(int userId) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from has_dayschedule where userid = " + userId;
        ResultSet rs = stmt.executeQuery(query);

        List<Has_DaySchedule> schedules = new ArrayList<>();
        int dt;
        Timestamp tf;
        Timestamp tt;

        while (rs.next()) {
            dt = rs.getInt("dayid");
            tf = rs.getTimestamp("timefrom");
            tt = rs.getTimestamp("timeto");
            schedules.add(new Has_DaySchedule(userId, dt, tf, tt));
            System.out.println(userId + " " + dt + " " + tf + " " + tt );
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
        Timestamp st;
        Timestamp et;

        while (rs.next()) {
            aid = rs.getInt("activityid");
            did = rs.getInt("doctorid");
            nid = rs.getInt("nurseid");
            st = rs.getTimestamp("starttime");
            et = rs.getTimestamp("endtime");
            activities.add(new HasActivty(aid, patientId, did, nid, st, et));
            System.out.println(aid + " " + patientId + " " + did + " " + nid + " " + st + " " + et );
        }
        return activities;
    }


    public List<Has_Bill> getBills(int patientid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from has_bill where patientid = " + patientid;
        ResultSet rs = stmt.executeQuery(query);

        int bid;
        float ad;
        float ap;
        Timestamp d;
        List<Has_Bill> bills = new ArrayList<>();

        while (rs.next()) {
            bid = rs.getInt("billid");
            ad = rs.getFloat("amountdue");
            ap = rs.getFloat("amountpaid");
            d = rs.getTimestamp("day");
            bills.add(new Has_Bill(patientid, bid, ad, ap, d));
        }
        return bills;
    }

    public boolean isOperationBill(int billid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from generatesoperationbill  where billid = " + billid;
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()){
            return true;
        }
        return false;
    }

    public boolean isBedBill(int billid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from generatesbedbill  where billid = " + billid;
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()){
            return true;
        }
        return false;
    }

    public boolean updateAmountPaid(int billid, float amountpaid) throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "update has_bill set amountpaid = " + amountpaid + " where billid = " + billid;
        int result = stmt.executeUpdate(query);
        return result == 1;
    }

    public Patient getPatient(int userid) throws  SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from patient, users where patient.patientid = users.userid and patient.patientid = " + userid;
        ResultSet rs = stmt.executeQuery(query);

        String un;
        String pw;
        String pn;
        String phn;
        Date db;
        String sx;
        String mh;

        Patient patient = null;

        while (rs.next()) {
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
            patient = (new Patient(un, pw, pn, phn, userid, db, sx, mh));
            System.out.println(un + " " + pw + " " + pn + " " + phn + " " + userid + " " + db + " " + sx + " " + mh );
        }
        return patient;
    }

    public Doctor getDoctor(int userid) throws  SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from doctor, users where doctorid = userid and doctorid = " + userid;
        ResultSet rs = stmt.executeQuery(query);

        String un;
        String pw;
        String dn;
        String phn;

        Doctor doctor = null;

        while (rs.next()) {
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
            doctor = new Doctor(un, pw, dn, phn, userid);
            System.out.println( un + " " + pw + " " + dn + " " + phn + " " + userid );
        }
        return doctor;
    }

    public Nurse getNurse(int userid) throws  SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from nurse, users where nurseid = userid and nurseid = " + userid;
        ResultSet rs = stmt.executeQuery(query);

        String un;
        String pw;
        String nn;
        String phn;

        Nurse nurse = null;

        while (rs.next()) {
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
            nurse = new Nurse(un, pw, nn, phn, userid);
            System.out.println(un + " " + pw + " " + nn + " " + phn + " " + userid );
        }
        return nurse;
    }

    public Receptionist getReceptionist(int userid) throws  SQLException {
        Statement stmt = connection.createStatement();
        String query = "select * from receptionist, users where receptionistid = userid and receptionistid = " + userid;
        ResultSet rs = stmt.executeQuery(query);

        String un;
        String pw;
        String nn;
        String phn;

        Receptionist receptionist = null;

        while (rs.next()) {
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
            receptionist = new Receptionist(un, pw, nn, phn, userid);
            System.out.println(un + " " + pw + " " + nn + " " + phn + " " + userid );
        }
        return receptionist;
    }

    public Pair<Integer, Float> getDoctorWithMaximumAverageOperationCost() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "select avgBill as maxSum, doctorid from (select doctorid, avg(amountdue) as avgBill " +
                "from has_bill h, generatesoperationbill g where g.billid = h.billid group by doctorid having avg(amountdue) > 0) " +
                "where avgBill >= (select max(avgBill) as maxSum from (select doctorid, avg(amountdue) as avgBill from has_bill h, " +
                "generatesoperationbill g where g.billid = h.billid group by doctorid having avg(amountdue) > 0))";

        ResultSet rs = stmt.executeQuery(query);

        Pair<Integer, Float> pair = null;
        Integer did;
        Float c;
        while (rs.next()) {
            did = rs.getInt("doctorid");
            c = rs.getFloat("maxSum");
            pair = new Pair<>(did, c);
            System.out.println(did + " " + c );
        }
        return pair;
    }

    public Pair<Integer, Float> getDoctorWithMinimumAverageOperationCost() throws  SQLException {
        Statement stmt = connection.createStatement();
        String query = "select avgBill as minSum, doctorid from (select doctorid, avg(amountdue) as avgBill " +
                "from has_bill h, generatesoperationbill g where g.billid = h.billid group by doctorid having avg(amountdue) > 0) " +
                "where avgBill <= (select min(avgBill) as minSum from (select doctorid, avg(amountdue) as avgBill from has_bill h, " +
                "generatesoperationbill g where g.billid = h.billid group by doctorid having avg(amountdue) > 0))";

        ResultSet rs = stmt.executeQuery(query);

        Pair<Integer, Float> pair = null;
        Integer did;
        Float c;
        while (rs.next()) {
            did = rs.getInt("doctorid");
            c = rs.getFloat("minSum");
            pair = new Pair<>(did, c);
            System.out.println( did + " " + c );
        }
        return pair;
    }

    public static void main(String[] args) {
        Database db = Database.getInstance();
        try {
        	// y - m - d - h - m - s - ns
        	Timestamp starttime = new Timestamp(1479329280000L);
        	Timestamp endtime = new Timestamp(1479329380000L);
        	System.out.println(db.addActivity( 3, 8, 17, starttime, endtime ));
        	/*
            List<Patient> patients = db.viewPatients(26, "m", "filler");
            patients = db.viewPatients(-1, "mf", "");
        	
        	System.out.println(db.checkUser(1,"d3rmpn"));
        	
            List<Patient> patients2 = db.getPatients();
        	
            for( Patient p : patients2 ){
            	System.out.println(p.getUserid() + p.getSex());
            }
            
            
            
            Pair<Integer, String> pair = db.findBedLocation(4);

            List<Pair<Integer, Float>> p = db.paidTheHighestBill();
            List<Pair<Integer, Float>> p2 = db.paidTheLowestBill();

            System.out.println(db.deleteBed(10));

            List<Integer> ps = db.monitoredByEveryNurse();
            for(Integer i : ps) {
                System.out.println(i);
            }

            db.addMonitors(4, 17, "hello");
            
            db.getDaySchedule(4);
            db.getPatientActivities(4);
            for (Patient pat : db.getPatients()) {
                System.out.println(pat.getPatientid());
            }
            for (Doctor doc: db.getDoctors()) {
                System.out.println(doc.getDoctorid());
            }
            for (Nurse nu: db.getNurses()) {
                System.out.println(nu.getNurseid());
            }
            for (Receptionist rep: db.getReceptionists()) {
                System.out.println(rep.getReceptionistid());
            }
            db.getMonitors(20);
            db.getDiagnosises(6);
            db.getPatient(3);
            db.getDoctor(6);
            db.getNurse(20);
            db.getReceptionist(14);
            db.getDoctorWithMaximumAverageOperationCost();
            db.getDoctorWithMinimumAverageOperationCost();*/
        } catch (SQLException | ActivityException e) {
            System.out.println("Message: " + e.getMessage());
            System.exit(-1);
        }
    }
}
