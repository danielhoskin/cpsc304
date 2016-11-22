package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import exceptions.ActivityException;
import database.Database;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by AddisonSasko on 2016-11-13.
 */
public class ActivityManagementFrame {

    // Parameters for the appearance of the GUI
    private static int WIDTH = 1500;
    private static int HEIGHT = 600;


    /*
    Panels and Tables for rendering the frame
    */
    static JFrame activityManagementFrame;
    JPanel mainPanel;
    JPanel activityManipulationPanel;
    JPanel activityNotesPanel;
    JTable patientTable;
    JTable doctorTable;
    JTable nurseTable;

    /*
    Buttons and listeners for the Frame.
     */
    private static JTextField patientNameText;
    private static JTextField doctorNameText;
    private static JTextField nurseNameText;
    private static JTextField startTimeText;
    private static JTextField endTimeText;

    private static JLabel notes;
    private static JLabel patientNameLabel;
    private static JLabel doctorNameLabel;
    private static JLabel nurseNameLabel;
    private static JLabel startTimeLabel;
    private static JLabel endTimeLabel;

    private static JButton submitButton;


    // Constructor for the ActivityManagementFrame
    public ActivityManagementFrame(JTable patientTable, JTable doctorTable, JTable nurseTable) {

        activityManagementFrame = new JFrame();
        activityManagementFrame.setSize(WIDTH,HEIGHT);
        activityManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        activityManagementFrame.setLayout(new FlowLayout());
        activityManagementFrame.setVisible(true);

        mainPanel = new JPanel();
        activityManagementFrame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        RenderTables(patientTable, doctorTable, nurseTable);
        RenderInteractiveSpace();

    }


    /*
     Constructs the patient, doctor and nurse tables for the frame
    */
    private void RenderTables(JTable patientTable, JTable doctorTable, JTable nurseTable) {

        activityManagementFrame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel patientPanel = new JPanel();
        patientPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Patients",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.patientTable = patientTable;
        JScrollPane patientScrollPane = new JScrollPane(this.patientTable);
        patientPanel.add(patientScrollPane, BorderLayout.WEST);
        mainPanel.add(patientPanel);

        JPanel doctorPanel = new JPanel();
        doctorPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Doctors",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.doctorTable = doctorTable;
        JScrollPane doctorScrollPane = new JScrollPane(this.doctorTable);
        doctorPanel.add(doctorScrollPane, BorderLayout.CENTER);
        mainPanel.add(doctorPanel);

        JPanel nursePanel = new JPanel();
        nursePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Nurse",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.nurseTable = nurseTable;
        JScrollPane nurseScrollPane = new JScrollPane(this.nurseTable);
        nursePanel.add(nurseScrollPane, BorderLayout.EAST);
        mainPanel.add(nursePanel);

    }


    /*
     Constructs the buttons and text fields for the frame
    */
    private void RenderInteractiveSpace(){

        activityManipulationPanel = new JPanel();
        activityManipulationPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Schedule Activity",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        activityManipulationPanel.setLayout(new FlowLayout());
        activityManagementFrame.add(activityManipulationPanel);

        patientNameLabel = new JLabel("Patient ID:");
        patientNameLabel.setBounds(10,40,80,25);
        activityManipulationPanel.add(patientNameLabel);
        patientNameText = new JTextField(10);
        patientNameText.setBounds(100, 40, 50, 25);
        activityManipulationPanel.add(patientNameText);

        doctorNameLabel = new JLabel("Doctor ID:");
        doctorNameLabel.setBounds(10,40,80,25);
        activityManipulationPanel.add(doctorNameLabel);
        doctorNameText = new JTextField(10);
        doctorNameText.setBounds(100, 40, 50, 25);
        activityManipulationPanel.add(doctorNameText);

        nurseNameLabel = new JLabel("Nurse ID:");
        nurseNameLabel.setBounds(10,40,80,25);
        activityManipulationPanel.add(nurseNameLabel);
        nurseNameText = new JTextField(10);
        nurseNameText.setBounds(100, 40, 50, 25);
        activityManipulationPanel.add(nurseNameText);

        startTimeLabel = new JLabel("Start Time:");
        startTimeLabel.setBounds(10,40,80,25);
        activityManipulationPanel.add(startTimeLabel);
        startTimeText = new JTextField(10);
        startTimeText.setBounds(100, 40, 50, 25);
        activityManipulationPanel.add(startTimeText);

        endTimeLabel = new JLabel("End Time:");
        endTimeLabel.setBounds(10,40,80,25);
        activityManipulationPanel.add(endTimeLabel);
        endTimeText = new JTextField(10);
        endTimeText.setBounds(100, 40, 50, 25);
        activityManipulationPanel.add(endTimeText);

        submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener(new AddActivityListener());
        activityManipulationPanel.add(submitButton);


        activityNotesPanel = new JPanel();
        activityNotesPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Notes",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        activityNotesPanel.setLayout(new FlowLayout());
        activityManagementFrame.add(activityNotesPanel);

        notes = new JLabel("The format for time is YYYY-MM-DD HH:MM. Eg. 2016-11-21 12:45");
        notes.setBounds(10,40,80,25);
        activityNotesPanel.add(notes);


    }



    // Listener for AddActivity, queries the database and refreshes the table page if it is successful
    private static class AddActivityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String givenPatientID = patientNameText.getText();
            String givenDoctorID = doctorNameText.getText();
            String givenNurseId = nurseNameText.getText();
            String givenStartTime = startTimeText.getText();
            String givenEndTime = endTimeText.getText();
            
            if (givenPatientID.equals("") && givenDoctorID.equals("")){
                JOptionPane.showMessageDialog(null, "Please type a valid User ID and a valid Doctor ID");
                return;
            }

            if (givenPatientID.equals("")){
                JOptionPane.showMessageDialog(null, "Please type a valid User ID");
                return;
            }

            if (givenDoctorID.equals("")){
                JOptionPane.showMessageDialog(null, "Please type a valid Doctor ID");
                return;
            }
            int patientid = Integer.parseInt(givenPatientID);
            int doctorid = Integer.parseInt(givenDoctorID);
            int nurseid;
            
            if( givenNurseId.equals(""))
            	nurseid = 0;
            else
            	nurseid = Integer.parseInt(givenNurseId);

            boolean success = false;
            try{
            	success = Database.getInstance().addActivity(patientid, doctorid, nurseid, givenStartTime, givenEndTime);
            } catch (ActivityException er ) {
            	JOptionPane.showMessageDialog(null, er.getMessage());
                er.printStackTrace();
            } catch (SQLException er ) {
            	JOptionPane.showMessageDialog(null, "Unable to add Activity. Check if the id numbers are correct");
            	er.printStackTrace();
            }
            if( success )
            	JOptionPane.showMessageDialog(null, "Activity Added!");
        }
    }
}