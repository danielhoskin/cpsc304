package GUI;
import database.Database;
import tables.Monitors;
import tables.Nurse;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by AddisonSasko on 2016-11-12.
 */
public class PatientMonitoringFrame {

    private static int WIDTH = 1000;
    private static int HEIGHT = 650;

    static JFrame monitoringFrame;
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


    public PatientMonitoringFrame(JTable patientTable, JTable monitoringTable, Nurse nurse){
        monitoringFrame = new JFrame();
        monitoringFrame.setSize(WIDTH,HEIGHT);
        monitoringFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        monitoringFrame.setLayout(new FlowLayout());
        monitoringFrame.setVisible(true);

        mainPanel = new JPanel();
        monitoringFrame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        RenderTables(patientTable, monitoringTable);
        RenderInteractiveSpace(nurse, patientTable);
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
    private void RenderInteractiveSpace(Nurse nurse, JTable patientTable){
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

        noteLabel = new JLabel("Notes");
        noteLabel.setBounds(10,40,80,25);
        buttonPanel.add(noteLabel);
        noteText = new JTextField(10);
        noteText.setBounds(100,40,50,25);
        buttonPanel.add(noteText);

        submitButton = new JButton();
        submitButton.setText("Add Patient");
        submitButton.addActionListener(new AddListener(nurse, patientTable));
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
        removeButton.addActionListener(new RemoveListener( nurse, patientTable));
        removeButtonPanel.add(removeButton);

        criticalPatients = new JButton();
        criticalPatients.setText("Critical Patients");
        criticalPatients.addActionListener(new CriticalPatientsListener());
        monitoringFrame.add(criticalPatients);

    }


    // Listener for AddActivity, queries the database and refreshes the table page if it is successful
    private static class RemoveListener implements ActionListener {
        private JTable patientTable;
        private Nurse nurse;

        RemoveListener(Nurse nurse, JTable patientTable) {
            this.nurse = nurse;
            this.patientTable = patientTable;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You pressed the submit button", "Submit Button", JOptionPane.INFORMATION_MESSAGE);
            try {
                int patientId = 0;
                if (patientNameTextRemove.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must specify the patient id.");
                    return;
                } else {
                    patientId = Integer.parseInt(patientNameTextRemove.getText());
                }
                if (Database.getInstance().deleteMonitors(nurse.getNurseid(), patientId)) { // TODO: need to add notes
                    JOptionPane.showMessageDialog(null, "Deleted monitors successfully.");

                    List<Monitors> monitors = Database.getInstance().getMonitors(nurse.getNurseid());
                    Iterator<Monitors> iterator = monitors.iterator();
                    Monitors monitor = null;
                    Object monitorsData[][] = new Object[monitors.size()][2];
                    for (int r = 0; r < monitors.size(); r++) {
                        monitor= iterator.next();
                        monitorsData[r][0] = monitor.getPatientid();
                        monitorsData[r][1] = monitor.getNotes();
                    }
                    Object monitorsColumnNames[] = {"PatientID", "Notes"} ;
                    JTable newTable= new JTable(monitorsData, monitorsColumnNames);

                    monitoringFrame.dispose();
                    monitoringFrame = new JFrame();
                    new PatientMonitoringFrame(patientTable, newTable, nurse);
                } else {
                    JOptionPane.showMessageDialog(null, "Unable to delete monitors.");
                }
            } catch (SQLException error1) {
                JOptionPane.showMessageDialog(null, "You specified an invalid patient.");
                error1.printStackTrace();
            }
        }
    }


    // Listener for AddActivity, queries the database and refreshes the table page if it is successful
    private static class AddListener implements ActionListener {
        private JTable patientTable;
        private Nurse nurse;

        AddListener(Nurse nurse, JTable patientTable) {
            this.nurse = nurse;
            this.patientTable = patientTable;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You pressed the submit button", "Submit Button", JOptionPane.INFORMATION_MESSAGE);
            try {
                int patientId = 0;
                //String notes = notesText.getText();
                if (patientNameText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You must specify the patient id.");
                    return;
                } else {
                    patientId = Integer.parseInt(patientNameText.getText());
                }
                if (Database.getInstance().addMonitors(patientId, nurse.getNurseid(), "")) { // TODO: need to add notes
                    JOptionPane.showMessageDialog(null, "Added monitors successfully.");

                    List<Monitors> monitors = Database.getInstance().getMonitors(nurse.getNurseid());
                    Iterator<Monitors> iterator = monitors.iterator();
                    Monitors monitor = null;
                    Object monitorsData[][] = new Object[monitors.size()][2];
                    for (int r = 0; r < monitors.size(); r++) {
                        monitor= iterator.next();
                        monitorsData[r][0] = monitor.getPatientid();
                        monitorsData[r][1] = monitor.getNotes();
                    }
                    Object monitorsColumnNames[] = {"PatientID", "Notes"} ;
                    JTable newTable= new JTable(monitorsData, monitorsColumnNames);

                    monitoringFrame.dispose();
                    monitoringFrame = new JFrame();
                    new PatientMonitoringFrame(patientTable, newTable, nurse);
                } else {
                    JOptionPane.showMessageDialog(null, "Unable to add monitors.");
                }
            } catch (SQLException error) {
                JOptionPane.showMessageDialog(null, "You specified an invalid patient.");
                error.printStackTrace();
            }
        }
    }

    // Listener for AddActivity, queries the database and refreshes the table page if it is successful
    private static class CriticalPatientsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "You pressed the CriticalPatientsListener button", "Submit Button", JOptionPane.INFORMATION_MESSAGE);
            try {
                List<Integer> pids = Database.getInstance().monitoredByEveryNurse();
                if (!pids.isEmpty()) {
                    Object mData[][] = new Object[0][0];
                    if (!pids.isEmpty()) {
                        mData = new Object[pids.size()][1];
                        Iterator<Integer> m_iterator = pids.iterator();
                        Integer pid = null;
                        for (int r = 0; r < pids.size(); r++) {
                            pid = m_iterator.next();
                            mData[r][0] = pid;
                        }
                    }
                    Object mColumnNames[] = {"Patient ID"};
                    JTable mTable = new JTable(mData, mColumnNames);
                    MedicalTable newTable = new MedicalTable(mTable);
                } else {
                    JOptionPane.showMessageDialog(null, "No patients are in critical condition.");
                }
            } catch (SQLException er) {
                JOptionPane.showMessageDialog(null, "Unable to render critical patients.");
                er.printStackTrace();
            }
        }
    }
}


