package GUI;
import tables.Nurse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by AddisonSasko on 2016-11-12.
 */
public class PatientMonitoringFrame {

    private static int WIDTH = 1000;
    private static int HEIGHT = 650;

    JFrame monitoringFrame;
    JPanel mainPanel;
    JPanel buttonPanel;
    JPanel removeButtonPanel;
    JTable patientTable;
    JTable monitoringTable;


    /*
    Buttons, text fields and labels for removing a monitors relation
     */
    private static JTextField patientID;
    private static JLabel relationDeleteLabel;
    //private static JButton removeButton;

    /*
    Buttons, text fields and labels for adding a new monitors relation
    */
    private static JTextField patientNameText;
    private static JLabel patientNameLabel;
    private static JButton submitButton;

    private static JTextField patientNameTextRemove;
    private static JLabel patientNameLabelRemove;
    private static JButton removeButton;


    private static JButton criticalPatients;
    private static JTextField noteText;
    private static JLabel nameLabel;
    private static JLabel noteLabel;
    private static JButton addButton;


    public PatientMonitoringFrame(JTable patientTable, JTable monitoringTable){
        monitoringFrame = new JFrame();
        monitoringFrame.setSize(WIDTH,HEIGHT);
        monitoringFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        monitoringFrame.setLayout(new FlowLayout());
        monitoringFrame.setVisible(true);

        mainPanel = new JPanel();
        monitoringFrame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        RenderTables(patientTable, monitoringTable);
        RenderInteractiveSpace();
    }

    // Renders the tables for the frame
    private void RenderTables(JTable patientTable, JTable monitoringTable){
        JPanel patientPanel = new JPanel();
        patientPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Patients",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.patientTable = patientTable;
        JScrollPane patientScrollPane = new JScrollPane(this.patientTable);
        patientPanel.add(patientScrollPane, BorderLayout.WEST);
        mainPanel.add(patientPanel);

        JPanel monitoringPanel = new JPanel();
        monitoringPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Monitors",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.monitoringTable = monitoringTable;
        JScrollPane monitorsScrollPane = new JScrollPane(this.monitoringTable);
        monitoringPanel.add(monitorsScrollPane, BorderLayout.EAST);
        mainPanel.add(monitoringPanel);
    }

    // Renders the interactive space for the frame
    private void RenderInteractiveSpace(){
        buttonPanel = new JPanel();
        buttonPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Add Patient To Monitors",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        buttonPanel.setLayout(new FlowLayout());
        monitoringFrame.add(buttonPanel);

        patientNameLabel = new JLabel("Patient ID:");
        patientNameLabel.setBounds(10,40,80,25);
        buttonPanel.add(patientNameLabel);
        patientNameText = new JTextField(10);
        patientNameText.setBounds(100,40,50,25);
        buttonPanel.add(patientNameText);

        submitButton = new JButton();
        submitButton.setText("Add Patient");
        submitButton.addActionListener(new AddSubmitListener());
        buttonPanel.add(submitButton);

        removeButtonPanel = new JPanel();
        removeButtonPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Remove Patient From Monitors",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        removeButtonPanel.setLayout(new FlowLayout());
        monitoringFrame.add(removeButtonPanel);

        patientNameLabelRemove = new JLabel("Patient ID:");
        patientNameLabelRemove.setBounds(10,40,80,25);
        removeButtonPanel.add(patientNameLabelRemove);
        patientNameTextRemove = new JTextField(10);
        patientNameTextRemove.setBounds(100,40,50,25);
        removeButtonPanel.add(patientNameTextRemove);

        removeButton = new JButton();
        removeButton.setText("Remove Patient");
        removeButton.addActionListener(new AddSubmitListener());
        removeButtonPanel.add(removeButton);

        criticalPatients = new JButton();
        criticalPatients.setText("Critical Patients");
        criticalPatients.addActionListener(new CriticalPatientsListener());
        monitoringFrame.add(criticalPatients);

    }

    // Listener for AddActivity, queries the database and refreshes the table page if it is successful
    private static class AddSubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You pressed the submit button", "Submit Button", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Listener for AddActivity, queries the database and refreshes the table page if it is successful
    private static class CriticalPatientsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You pressed the CriticalPatientsListener button", "Submit Button", JOptionPane.INFORMATION_MESSAGE);
        }
    }




        /*
        monitoringFrame = new JFrame("Patient Monitoring");
        monitoringFrame.setSize(WIDTH,HEIGHT);
        monitoringFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        monitoringFrame.setLayout(new FlowLayout());

        mainPanel = new JPanel();
        monitoringFrame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        addMonitorsRelationPanel = new JPanel();
        addMonitorsRelationPanel.setLayout(new FlowLayout());
        EmptyBorder panelBorder = new EmptyBorder(10, 10, 10, 10);
        addMonitorsRelationPanel.setBorder(panelBorder);
        buttonPanel.add(addMonitorsRelationPanel);

        nameLabel = new JLabel("Patient Name:");
        nameLabel.setBounds(10,40,80,25);
        addMonitorsRelationPanel.add(nameLabel);

        patientNameText = new JTextField(20);
        patientNameText.setBounds(100, 40, 50, 25);
        addMonitorsRelationPanel.add(patientNameText);

        noteLabel = new JLabel("Patient Username:");
        noteLabel.setBounds(100, 40, 50, 25);
        addMonitorsRelationPanel.add(noteLabel);

        noteText = new JTextField(20);
        noteText.setBounds(100, 40, 50, 25);
        addMonitorsRelationPanel.add(noteText);

        addButton = new JButton();
        addButton.setText("Add Patient");
        addMonitorsRelationPanel.add(addButton);

        removeMonitorsRelationPanel = new JPanel();
        removeMonitorsRelationPanel.setLayout(new FlowLayout());
        removeMonitorsRelationPanel.setBorder(panelBorder);
        buttonPanel.add(removeMonitorsRelationPanel);

        relationDeleteLabel = new JLabel("Patient ID:");
        relationDeleteLabel.setBounds(10,40,80,25);
        removeMonitorsRelationPanel.add(relationDeleteLabel);

        patientID = new JTextField(20);
        patientID.setBounds(100, 40, 50, 25);
        removeMonitorsRelationPanel.add(patientID);

        removeButton = new JButton();
        removeButton.setText("Remove Patient");
        removeMonitorsRelationPanel.add(removeButton);


        JPanel patientPannel = new JPanel ();
        patientPannel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Patients",
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
        patientTable = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(patientTable);
        patientPannel.add(scrollPane, BorderLayout.CENTER);
        monitoringFrame.add(patientPannel);

        JPanel monitorsPanel = new JPanel();
        monitorsPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Currently Monitoring",
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
        monitoringTable = new JTable(activityData, columnNamesData);
        JScrollPane activityScrollPane = new JScrollPane(monitoringTable);
        monitorsPanel.add(activityScrollPane, BorderLayout.CENTER);
        monitoringFrame.add(monitorsPanel);


        monitoringFrame.setVisible(true);
        */

}


