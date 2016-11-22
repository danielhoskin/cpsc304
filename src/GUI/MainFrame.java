package GUI;
/**
 * Created by AddisonSasko on 2016-11-11.
 */

import database.Database;
import lib.ThreePair;
import tables.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class MainFrame {

    private static int WIDTH = 1000;
    private static int HEIGHT = 650;
    private static Database mainConnection;

    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel buttonPanel;
    private static JPanel informationPanel;
    private static JPanel medicalHistoryPanel;
    private static JPanel schedulePanel;
    private static JPanel activityPannel;

    // Constructs a mainframe for a patient user
    public MainFrame(Patient patientUser){
        mainConnection = Database.getInstance();
        System.out.println("patient: " + patientUser.getName());

        generateStructure(patientUser);

        JLabel name = new JLabel("Name: " + patientUser.getName());
        informationPanel.add(name);
        JLabel sex = new JLabel("Sex: " + patientUser.getSex());
        informationPanel.add(sex);
        JLabel dateOfBirth = new JLabel("DateOfBirth: " + patientUser.getDateofbirth());
        informationPanel.add(dateOfBirth);
        JLabel userID = new JLabel("UserId: " + patientUser.getUserid());
        informationPanel.add(userID);
        JLabel password = new JLabel("Password: " + patientUser.getPassword());
        informationPanel.add(password);
        JLabel phoneNumber = new JLabel("PhoneNumber: " + patientUser.getPhonenumber());
        informationPanel.add(phoneNumber);
        JLabel userType = new JLabel("User Type: Patient");
        informationPanel.add(userType);
        JLabel userMedicalHistory = new JLabel("Medical History: " + patientUser.getMedicalhistory());
        medicalHistoryPanel.add(userMedicalHistory);

        frame.setVisible(true);
    }

    // Constructs a mainframe for a doctor user
    public MainFrame(Doctor doctorUser){
        mainConnection = Database.getInstance();
        generateStructure(doctorUser);

        JButton projection = new JButton();
        projection.setText("View Patients");
        projection.addActionListener(new ViewPatientsListener(doctorUser));
        buttonPanel.add(projection);

        JButton diagnosis = new JButton();
        diagnosis.setText("Diagnosis Management");
        diagnosis.addActionListener(new DiagnosisListener(doctorUser));
        buttonPanel.add(diagnosis);

        JLabel name = new JLabel("Name: " + doctorUser.getName());
        informationPanel.add(name);

        JLabel userID = new JLabel("UserId: " + doctorUser.getUserid());
        informationPanel.add(userID);

        JLabel password = new JLabel("Password: " + doctorUser.getPassword());
        informationPanel.add(password);

        JLabel phoneNumber = new JLabel("PhoneNumber: " + doctorUser.getPhonenumber());
        informationPanel.add(phoneNumber);

        frame.setVisible(true);

    }

    // Constructs a mainframe for a nurse user
    public MainFrame(Nurse nurseUser){
        mainConnection = Database.getInstance();
        generateStructure(nurseUser);

        JButton division = new JButton();
        division.setText("Patient Monitoring");
        division.addActionListener(new DivisionListener(nurseUser));
        buttonPanel.add(division);

        JLabel name = new JLabel("Name: " + nurseUser.getName());
        informationPanel.add(name);

        JLabel userID = new JLabel("UserId: " + nurseUser.getUserid());
        informationPanel.add(userID);

        JLabel password = new JLabel("Password: " + nurseUser.getPassword());
        informationPanel.add(password);

        JLabel phoneNumber = new JLabel("PhoneNumber: " + nurseUser.getPhonenumber());
        informationPanel.add(phoneNumber);

        frame.setVisible(true);

    }

    // Constructs a mainframe for a receptionist user
    public MainFrame(Receptionist receptionistUser){
        mainConnection = Database.getInstance();
        generateStructure(receptionistUser);

        JButton nestedAggregation = new JButton();
        nestedAggregation.setText("Billing Management");
        nestedAggregation.addActionListener(new NestedAggregationListener());
        buttonPanel.add(nestedAggregation);

        JButton update = new JButton();
        update.setText("Activity Management");
        update.addActionListener(new UpdateListener());
        buttonPanel.add(update);

        JLabel name = new JLabel("Name: " + receptionistUser.getName());
        informationPanel.add(name);

        JLabel userID = new JLabel("UserId: " + receptionistUser.getUserid());
        informationPanel.add(userID);

        JLabel password = new JLabel("Password: " + receptionistUser.getPassword());
        informationPanel.add(password);

        JLabel phoneNumber = new JLabel("PhoneNumber: " + receptionistUser.getPhonenumber());
        informationPanel.add(phoneNumber);

        frame.setVisible(true);

    }

    /*
    Generates the structure of the frame, this is universal for all user types
     */
    private void generateStructure(Users u){
        frame = new JFrame("Harambase Inc");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());

        informationPanel = new JPanel();
        mainPanel.add(informationPanel);
        informationPanel.setLayout(new GridLayout(2, 5 , 8, 8));
        informationPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "User Information",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        medicalHistoryPanel = new JPanel();
        mainPanel.add(medicalHistoryPanel);
        medicalHistoryPanel.setLayout(new GridLayout(2, 5, 8, 8));
        medicalHistoryPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Medical History",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        JPanel overlaySchedulesPanel = new JPanel();
        mainPanel.add(overlaySchedulesPanel);
        overlaySchedulesPanel.setLayout(new BoxLayout(overlaySchedulesPanel, BoxLayout.X_AXIS));

        schedulePanel = new JPanel ();
        schedulePanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Schedule",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        Object[][] rowData = new Object[7][3];
        try {
            List<ThreePair<String, String, String>> schedules = mainConnection.getDaySchedule(u.getUserid());
            Iterator<ThreePair<String, String, String>> iterator = schedules.iterator();
            ThreePair<String, String, String> schedule = null;
            for (int r = 0; r < 7; r++) {
                schedule = iterator.next();
                rowData[r][0] = schedule.getLeft();
                rowData[r][1] = schedule.getMiddle();
                rowData[r][2] = schedule.getRight();
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to render schedule for user: " + u.getUserid());
            e.printStackTrace();
        }

        Object columnNames[] = { "Day", "Available From", "Available To" } ;
        JTable timetable = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(timetable);
        schedulePanel.add(scrollPane, BorderLayout.CENTER);
        overlaySchedulesPanel.add(schedulePanel);

        try {
            if (!mainConnection.isReceptionist(u.getUserid())) {
                activityPannel = new JPanel();
                activityPannel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                        "Activities",
                        TitledBorder.CENTER,
                        TitledBorder.TOP));
                List<HasActivty> activities = null;
                int userId = u.getUserid();
                if (mainConnection.isPatient(userId)) {
                    activities = mainConnection.getPatientActivities(userId);
                } else if (mainConnection.isDoctor(userId)) {
                    activities = mainConnection.getDoctorActivities(userId);
                } else {
                    activities = mainConnection.getNurseActivities(userId);
                }

                Object[][] activityData = new Object[activities.size()][4];
                Iterator<HasActivty> iterator = activities.iterator();
                HasActivty activity = null;
                for (int r = 0; r < activities.size(); r++) {
                    activity = iterator.next();
                    activityData[r][0] = activity.getActivityid();
                    activityData[r][1] = mainConnection.getActivityDescription(activity.getActivityid());
                    activityData[r][2] = activity.getStarttime();
                    activityData[r][3] = activity.getEndtime();
                }

                Object columnNamesData[] = {"ActivityId", "Activity", "Start Time", "End Time"};
                JTable activityTable = new JTable(activityData, columnNamesData);
                JScrollPane activityScrollPane = new JScrollPane(activityTable);
                activityPannel.add(activityScrollPane, BorderLayout.CENTER);
                overlaySchedulesPanel.add(activityPannel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to render activities for user: " + u.getUserid());
            e.printStackTrace();
        }
    }


    /*
    ALL LISTENERS FOR MAINFRAME.
     */

    private static class ViewPatientsListener implements ActionListener {
        private Doctor doctor;

        public ViewPatientsListener(Doctor d) {
            this.doctor = d;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Patient> patients = mainConnection.getPatients();
                Iterator<Patient> iterator = patients.iterator();
                Patient patient = null;
                Object patientData[][] = new Object[patients.size()][6];
                for (int r = 0; r < patients.size(); r++) {
                    patient = iterator.next();
                    patientData[r][0] = patient.getName();
                    patientData[r][1] = patient.getUserid();
                    patientData[r][2] = patient.getSex();
                    patientData[r][3] = patient.getDateofbirth();
                    patientData[r][4] = patient.getMedicalhistory();
                    patientData[r][5] = patient.getPhonenumber();
                }
                Object patientColumnNames[] = { "PatientName", "PatientID", "Sex", "Date of Birth","Ailment", "Contact Details" } ;
                JTable patientTable = new JTable(patientData, patientColumnNames);

                PatientViewFrame patientView = new PatientViewFrame(patientTable);
            } catch(SQLException er) {
                JOptionPane.showMessageDialog(null, "Unable to render patients.");
                er.printStackTrace();
            }
        }
    }

    private static class DivisionListener implements ActionListener {
        private Nurse nurse;

        public DivisionListener(Nurse nurse) {
            this.nurse = nurse;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You Pressed the Patient Monitoring button.");

            try {
                List<Monitors> monitors = mainConnection.getMonitors(nurse.getNurseid());
                Iterator<Monitors> iterator = monitors.iterator();
                Monitors monitor = null;
                Object monitorsData[][] = new Object[monitors.size()][2];
                for (int r = 0; r < monitors.size(); r++) {
                    monitor= iterator.next();
                    monitorsData[r][0] = monitor.getPatientid();
                    monitorsData[r][1] = monitor.getNotes();
                }
                Object monitorsColumnNames[] = {"PatientID", "Notes"} ;
                JTable monitorsTable = new JTable(monitorsData, monitorsColumnNames);

                List<Patient> patients = mainConnection.getPatients();
                Iterator<Patient> p_iterator = patients.iterator();
                Patient patient = null;
                Object patientData[][] = new Object[patients.size()][3];
                for (int r = 0; r < patients.size(); r++) {
                    patient = p_iterator.next();
                    patientData[r][0] = patient.getName();
                    patientData[r][1] = patient.getUserid();
                }
                Object patientColumnNames[] = { "PatientName", "PatientID"} ;
                JTable patientTable = new JTable(patientData, patientColumnNames);


                PatientMonitoringFrame newPatientMonitoringFrame = new PatientMonitoringFrame(patientTable,monitorsTable, nurse);
            } catch(SQLException er) {
                JOptionPane.showMessageDialog(null, "Unable to render monitors.");
                er.printStackTrace();
            }

        }
    }

    private static class NestedAggregationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Has_Bill> bills = mainConnection.getBills();
                Iterator<Has_Bill> iterator = bills.iterator();
                Has_Bill bill = null;
                Object billData[][] = new Object[bills.size()][4];
                for (int r = 0; r <bills.size(); r++) {
                    bill = iterator.next();
                    billData[r][0] = bill.getPatientid();
                    billData[r][1] = bill.getBillid();
                    billData[r][2] = bill.getAmountdue();
                    billData[r][3] = bill.getAmountpaid();
                }
                JOptionPane.showMessageDialog(null, "You Pressed the Billing Management button.");
                Object billColumnNames[] = { "Patient ID", "Bill ID", "Amount Due", "Amount Paid"} ;
                JTable billTable = new JTable(billData, billColumnNames);
                BillingManagementFrame billingManagementFrame = new BillingManagementFrame(billTable);
            } catch(SQLException er) {
                JOptionPane.showMessageDialog(null, "Unable to render bills.");
                er.printStackTrace();
            }
        }
    }

    private static class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Patient> patients = mainConnection.getPatients();
                Iterator<Patient> iterator = patients.iterator();
                Patient patient = null;
                Object patientData[][] = new Object[patients.size()][3];
                for (int r = 0; r < patients.size(); r++) {
                    patient = iterator.next();
                    patientData[r][0] = patient.getName();
                    patientData[r][1] = patient.getUserid();
                    patientData[r][2] = patient.getSex();
                }
                Object patientColumnNames[] = { "Patient Name", "Patient ID", "Sex"} ;
                JTable patientTable = new JTable(patientData, patientColumnNames);

                List<Doctor> doctors = mainConnection.getDoctors();
                Iterator<Doctor> iterator_d = doctors.iterator();
                Doctor doctor = null;
                Object doctorData[][] = new Object[doctors.size()][2];
                for (int r = 0; r < doctors.size(); r++) {
                    doctor = iterator_d.next();
                    doctorData[r][0] = doctor.getName();
                    doctorData[r][1] = doctor.getUserid();
                }
                Object doctorColumnNames[] = { "Doctor Name", "Doctor ID"} ;
                JTable doctorTable = new JTable(doctorData, doctorColumnNames);

                List<Nurse> nurses = mainConnection.getNurses();
                Iterator<Nurse> iterator_n= nurses.iterator();
                Nurse nurse = null;
                Object nurseData[][] = new Object[nurses.size()][2];
                for (int r = 0; r < nurses.size(); r++) {
                    nurse = iterator_n.next();
                    nurseData[r][0] = nurse.getName();
                    nurseData[r][1] = nurse.getUserid();
                }
                Object nurseColumnNames[] = { "Nurse Name", "Nurse ID" } ;
                JTable nurseTable = new JTable(nurseData, nurseColumnNames);


                JOptionPane.showMessageDialog(null, "You Pressed the Activity Management button.");
                ActivityManagementFrame activityManagementFrame = new ActivityManagementFrame(patientTable,doctorTable,nurseTable);
            } catch(SQLException er) {
                JOptionPane.showMessageDialog(null, "Unable to render tables.");
                er.printStackTrace();
            }
        }
    }

    private static class DiagnosisListener implements ActionListener {
        private  Doctor doctor;

        public DiagnosisListener(Doctor doctor) {
            this.doctor = doctor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Has_Diagnosis> diagnosises = mainConnection.getDoctorDiagnosises(doctor.getUserid());
                Iterator<Has_Diagnosis> iterator = diagnosises.iterator();
                Has_Diagnosis diagnosis = null;
                Object diagnosisID[][] = new Object[diagnosises.size()][3];
                for (int r = 0; r < diagnosises.size(); r++) {
                    diagnosis = iterator.next();
                    diagnosisID[r][0] = diagnosis.getPatientid();
                    diagnosisID[r][1] = doctor.getUserid();
                    diagnosisID[r][2] = diagnosis.getDiagnosisid();
                }
                Object diagnosisColumnNames[] = { "Patient ID", "Doctor ID", "Diagnosis ID"} ;
                JTable diagnosisTable = new JTable(diagnosisID, diagnosisColumnNames);

                JOptionPane.showMessageDialog(null, "You Pressed the Diagnosis Management button.");
                DiagnosisManagementFrame diagnosisManagementFrame = new DiagnosisManagementFrame(diagnosisTable, doctor);
            } catch(SQLException er) {
                JOptionPane.showMessageDialog(null, "Unable to render diagnosises.");
                er.printStackTrace();
            }
        }

    }
}
