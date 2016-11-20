package GUI;
import database.Database;
import tables.Doctor;
import tables.Has_Diagnosis;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by AddisonSasko on 2016-11-13.
 */
public class DiagnosisManagementFrame {


    private static int WIDTH = 800;
    private static int HEIGHT = 600;

    /*
    Panels and Tables for rendering the frame
    */
    static JFrame diagnosisManagementFrame;
    JPanel mainPanel;
    JTable diagnosisTable;
    JPanel diagnosisManipulationPanel;


    /*
    Buttons and listeners for the Frame.
     */
    private static JTextField patientNameText;
    private static JTextField doctorNameText;
    private static JTextField diagnosisIdText;
    private static JLabel patientNameLabel;
    private static JLabel doctorNameLabel;
    private static JLabel diagnosisIdLabel;

    private static JButton submitButton;

    private static Doctor doctor;


    // Constructor for DiagnosisManagement
    public DiagnosisManagementFrame(JTable DiagnosisTable, Doctor doctor){
        this.doctor = doctor;
        diagnosisManagementFrame = new JFrame();
        diagnosisManagementFrame.setSize(WIDTH,HEIGHT);
        diagnosisManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        diagnosisManagementFrame.setLayout(new FlowLayout());
        diagnosisManagementFrame.setVisible(true);

        mainPanel = new JPanel();
        diagnosisManagementFrame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        RenderTables(DiagnosisTable);
        RenderInteractiveSpace();
    }


    // Renders the tables for the frame
    private void RenderTables(JTable DiagnosisTable){

        JPanel diagnosisPanel = new JPanel();
        diagnosisPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Diagnosis",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.diagnosisTable = DiagnosisTable;
        JScrollPane diagnosisScrollPane = new JScrollPane(this.diagnosisTable);
        diagnosisPanel.add(diagnosisScrollPane, BorderLayout.CENTER);
        mainPanel.add(diagnosisPanel);

    }


    // Renders the interactive space for the frame: buttons, texts and tags
    private void RenderInteractiveSpace(){
        diagnosisManipulationPanel = new JPanel();
        diagnosisManipulationPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Remove Diagnosis",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        diagnosisManipulationPanel.setLayout(new FlowLayout());
        diagnosisManagementFrame.add(diagnosisManipulationPanel);

        patientNameLabel = new JLabel("Patient ID:");
        patientNameLabel.setBounds(10,40,80,25);
        diagnosisManipulationPanel.add(patientNameLabel);
        patientNameText = new JTextField(10);
        patientNameText.setBounds(100, 40, 50, 25);
        diagnosisManipulationPanel.add(patientNameText);

        doctorNameLabel = new JLabel("Doctor ID:");
        doctorNameLabel.setBounds(10,40,80,25);
        diagnosisManipulationPanel.add(doctorNameLabel);
        doctorNameText = new JTextField(10);
        doctorNameText.setBounds(100, 40, 50, 25);
        diagnosisManipulationPanel.add(doctorNameText);

        diagnosisIdLabel = new JLabel("Diagnosis ID:");
        diagnosisIdLabel.setBounds(10,40,80,25);
        diagnosisManipulationPanel.add(diagnosisIdLabel);
        diagnosisIdText = new JTextField(10);
        diagnosisIdText.setBounds(100, 40, 50, 25);
        diagnosisManipulationPanel.add(diagnosisIdText);

        submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener(new removeDiagnosisListener());
        diagnosisManipulationPanel.add(submitButton);
    }


    // Listener for AddActivity, queries the database and refreshes the table page if it is successful
    private static class removeDiagnosisListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String givenPatientID = patientNameText.getText();
            String givenDoctorID = doctorNameText.getText();
            String givenDiagnosisID = diagnosisIdText.getText();

            if (givenDiagnosisID.equals("")){
                final ImageIcon icon = new ImageIcon("/Users/AddisonSasko/Desktop/comfy.jpg");
                JOptionPane.showMessageDialog(null, "Please provide a valid Diagnosis ID", "Error", JOptionPane.INFORMATION_MESSAGE ,icon);
            }
            try {
                if (Database.getInstance().deleteDiagnosis(Integer.parseInt(givenDiagnosisID))) {
                    JOptionPane.showMessageDialog(null, "Deleted " + givenDiagnosisID + " successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Could not delete diagnosis : " + givenDiagnosisID + ".", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                List<Has_Diagnosis> diagnosises = Database.getInstance().getDoctorDiagnosises(doctor.getUserid());
                Object diagnosisID[][] = new Object[0][0];
                if (!diagnosises.isEmpty()) {
                    diagnosisID = new Object[diagnosises.size()][3];
                    Iterator<Has_Diagnosis> iterator = diagnosises.iterator();
                    Has_Diagnosis diagnosis = null;
                    for (int r = 0; r < diagnosises.size(); r++) {
                        diagnosis = iterator.next();
                        diagnosisID[r][0] = diagnosis.getPatientid();
                        diagnosisID[r][1] = doctor.getUserid();
                        diagnosisID[r][2] = diagnosis.getDiagnosisid();
                    }
                }
                Object diagnosisColumnNames[] = { "Patient ID", "Doctor ID", "Diagnosis ID"} ;
                JTable diagnosisTable = new JTable(diagnosisID, diagnosisColumnNames);
                diagnosisManagementFrame.dispose();
                diagnosisManagementFrame = new JFrame();
                new DiagnosisManagementFrame(diagnosisTable, doctor);
            }
            catch(SQLException er) {
                JOptionPane.showMessageDialog(null, "Unable to render diagnosises.");
                er.printStackTrace();
            }
            catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(null, "Unable to parse diagnosis id.");
                err.printStackTrace();
            }
        }
    }


}
