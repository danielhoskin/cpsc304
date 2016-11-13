/**
 * Created by AddisonSasko on 2016-11-11.
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    
    private static int WIDTH = 1000;
    private static int HEIGHT = 650;
    
    // public constructor for the mainFrame
    public static void main(String[] args){
        initialiseComponents(new User("John", 3));
    }
    
    
    // Initialise the different components of the frame
    private static void initialiseComponents(User newUser){
        
        // Initialises the new plane, with relevant fields, including the default close operation
        JFrame frame = new JFrame("Harambase Inc");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        
        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        
        // Button Panel: Includes buttons for selecting operations on the data.
        JPanel buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());
        
        // Information Pannel: Includes labels for information about the user including UserId, Username, Password
        // Phone Number and Name. For patients there will also be information about sex, date of birth,
        // and MedicalHistory.
        JPanel informationPanel = new JPanel();
        mainPanel.add(informationPanel);
        informationPanel.setLayout(new GridLayout(2, 5 , 8, 8));
        EmptyBorder panelBorder = new EmptyBorder(10, 10, 10, 10);
        informationPanel.setBorder(panelBorder);
        
        
        
        JPanel medicalHistoryPanel = new JPanel();
        mainPanel.add(medicalHistoryPanel);
        medicalHistoryPanel.setLayout(new GridLayout(2, 5, 8, 8));
        medicalHistoryPanel.setBorder(panelBorder);
        
        
        
        JPanel schedulePannel = new JPanel ();
        schedulePannel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                                                                    "Schedule",
                                                                    TitledBorder.CENTER,
                                                                    TitledBorder.TOP));
        
        Object rowData[][] = {
            { "Monday", "8:00", "20:00" },
            { "Tuesday", "8:00", "20:00" },
            { "Wednesday", "8:00", "20:00" },
            { "Thursday", "8:00", "20:00" },
            { "Friday", "8:00", "20:00" },
            { "Saturday", "8:00", "20:00" },
            { "Sunday", "8:00", "20:00" }};
        Object columnNames[] = { "Day", "Available From", "Available To" } ;
        JTable timetable = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(timetable);
        schedulePannel.add(scrollPane, BorderLayout.CENTER);
        frame.add(schedulePannel);
        
        
        
        JPanel activityPannel = new JPanel();
        activityPannel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                                                                    "Activites",
                                                                    TitledBorder.CENTER,
                                                                    TitledBorder.TOP));
        
        Object activityData[][] = {
            { "908543289","Nap Time", "8:00", "20:00" },
            { "543289504","Alone Time", "8:00", "20:00" },
            { "245049943","Walking Time", "8:00", "20:00" },
            { "543254454","Talking Time", "8:00", "20:00" },
            { "432543254","TV Time", "8:00", "20:00" },
            { "543254324","Sleeping Time", "8:00", "20:00" },
            { "432632463","Eating Time", "8:00", "20:00" },};
        Object columnNamesData[] = { "ActivityId", "Activity", "Start Time", "End Time" } ;
        JTable activityTable = new JTable(activityData, columnNamesData);
        JScrollPane activityScrollPane = new JScrollPane(activityTable);
        activityPannel.add(activityScrollPane, BorderLayout.CENTER);
        frame.add(activityPannel);
        
        
        // Generate the buttons for the Button Pannel, including adding action listeners
        
        JButton projection = new JButton();
        projection.setText("View Patients");
        projection.addActionListener(new ViewPatientsListener());
        buttonPanel.add(projection);
        
        JButton join = new JButton();
        join.setText("Join");
        join.addActionListener(new JoinListener());
        buttonPanel.add(join);
        
        JButton division = new JButton();
        division.setText("Division");
        division.addActionListener(new DivisionListener());
        buttonPanel.add(division);
        
        JButton aggregation = new JButton();
        aggregation.setText("Aggregation");
        aggregation.addActionListener(new AggregationListener());
        buttonPanel.add(aggregation);
        
        JButton nestedAggregation = new JButton();
        nestedAggregation.setText("Nested Aggregation");
        nestedAggregation.addActionListener(new NestedAggregationListner());
        buttonPanel.add(nestedAggregation);
        
        JButton deletion = new JButton();
        deletion.setText("Patient Management");
        deletion.addActionListener(new DeletionListener());
        buttonPanel.add(deletion);
        
        JButton update = new JButton();
        update.setText("Patient Monitoring");
        update.addActionListener(new UpdateListener());
        buttonPanel.add(update);
        
        
        
        JLabel name = new JLabel("Name: John");
        informationPanel.add(name);
        
        if (newUser.getPrivilage() == 1) {
            JLabel sex = new JLabel("Sex: Male");
            informationPanel.add(sex);
        }
        
        JLabel userName = new JLabel("UserName: John51593");
        informationPanel.add(userName);
        
        if (newUser.getPrivilage() == 1) {
            JLabel dateOfBirth = new JLabel("DateOfBirth: July 15 1962");
            informationPanel.add(dateOfBirth);
        }
        
        if (newUser.getPrivilage() != 1) {
            JLabel userID = new JLabel("UserId: 52589132");
            informationPanel.add(userID);
        }
        
        
        
        JLabel password = new JLabel("Password: 123");
        informationPanel.add(password);
        
        
        if (newUser.getPrivilage() == 1) {
            JLabel phoneNumber = new JLabel("PhoneNumber: (778-991-9316)");
            informationPanel.add(phoneNumber);
        }
        
        switch (newUser.getPrivilage()){
                
            case 1:
                JLabel patientUser = new JLabel("UserType: Patient");
                informationPanel.add(patientUser);
                break;
            case 2:
                JLabel nurseUser = new JLabel("UserType: Nurse");
                informationPanel.add(nurseUser);
                break;
            case 3:
                
                JLabel doctorUser = new JLabel("UserType: Doctor");
                informationPanel.add(doctorUser);
                
                break;
                
            case 4:
                JLabel administrativeUser = new JLabel("UserType: Administration");
                informationPanel.add(administrativeUser);
                break;
        }
        
        if (newUser.getPrivilage() == 1) {
            JLabel medicalHistory = new JLabel("MedicalHistory: Bruised his knee once.");
            medicalHistoryPanel.add(medicalHistory);
            medicalHistoryPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        
        
        informationPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        frame.setVisible(true);
        
    }
    
    
    /*
     ALL LISTENERS FOR MAINFRAME.
     */
    
    
    private static class ViewPatientsListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Need to provide a table of all data once we generate the new screen!
            
            Object patientData[][] = {
                
                { "Ritchie","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Welles","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Capra","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Warhol","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Breillat","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Fellini","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Carpenter","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Jodorowsky","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Christopher Nolan","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "George Lucas","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Will Smith","5258932", "Male","18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Wallgreen","5258932", "Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Tarintino","5258932", "Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Michael Bay","5258932", "Male","18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Joseph Levit","5258932", "Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
            };
            Object patientColumnNames[] = { "PatientName", "PatientID", "Sex", "Date of Birth","Ailment", "Contact Details" } ;
            JTable patientTable = new JTable(patientData, patientColumnNames);
            
            PatientViewFrame patientView = new PatientViewFrame(patientTable);
        }
    }
    
    private static class JoinListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You Pressed the Join button.");
        }
    }
    
    private static class DivisionListener implements ActionListener {
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You Pressed the Patient Monitoring button.");
            
        }
    }
    
    private static class AggregationListener implements ActionListener {
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You Pressed the Aggregation button.");
            
        }
    }
    
    private static class NestedAggregationListner implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You Pressed the Nested Aggregation button.");
            
        }
    }
    
    private static class DeletionListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You Pressed the Patient Management button.");
            Object patientData[][] = {
                
                { "Ritchie","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Welles","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Capra","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Warhol","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Breillat","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Fellini","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Carpenter","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Jodorowsky","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Christopher Nolan","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "George Lucas","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Will Smith","5258932", "Male","18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Wallgreen","5258932", "Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Tarintino","5258932", "Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Michael Bay","5258932", "Male","18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Joseph Levit","5258932", "Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
            };
            Object patientColumnNames[] = { "PatientName", "PatientID", "Sex", "Date of Birth","Ailment", "Contact Details" } ;
            JTable patientTable = new JTable(patientData, patientColumnNames);
            
            PatientManagementFrame patientManagementView = new PatientManagementFrame(patientTable);
            
            
        }
    }
    
    private static class UpdateListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // JOptionPane.showMessageDialog(null, "You Pressed the Patient Monitoring button.");
            PatientMonitoringFrame monitoringFrame = new PatientMonitoringFrame();
        }
    }
    
    
}
