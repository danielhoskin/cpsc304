package GUI;
import database.Database;
import tables.Patient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Iterator;

/**
 * Created by AddisonSasko on 2016-11-11.
 */
public class PatientViewFrame {

    private static int WIDTH = 1000;
    private static int HEIGHT = 700;

    private static JTextField patientAgeText;
    private static JTextField patientSexText;
    private static JTextField patientConditionText;
    private static JLabel ageLabel;
    private static JLabel sexLabel;
    private static JLabel conditionLabel;
    private final JCheckBox nameBox;
    private final JCheckBox patientBox;
    private final JCheckBox birthDateBox;
    private final JCheckBox ailmentBox;
    private final JCheckBox contactBox;


    static JFrame patientFrame;
    JPanel mainPanel;
    JPanel patientPanel;


    public PatientViewFrame(JTable Table){

        // Initialises the frame for the patient monitoring frame.
        patientFrame = new JFrame("View Patients");
        patientFrame.setSize(WIDTH,HEIGHT);
        patientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        patientFrame.setLayout(new FlowLayout());


        // Initialises the main pannel for the frame, this is where we attatch all of the additional panels so that they
        // are arranged along the Y-Axis
        mainPanel = new JPanel();
        patientFrame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        // Initialises the patient panel, which stores the patient values.
        patientPanel = new JPanel();
        patientPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Patients",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        mainPanel.add(patientPanel);
        JScrollPane patientScrollPlane = new JScrollPane(Table);
        patientPanel.add(patientScrollPlane, BorderLayout.CENTER);


        // Button Panel: Includes buttons for selecting operations on the data.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Search Patients",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());


        ageLabel = new JLabel("Age");
        ageLabel.setBounds(10, 10, 80, 25);
        buttonPanel.add(ageLabel);


        patientAgeText = new JTextField(20);
        patientAgeText.setBounds(100, 10, 160, 25);
        buttonPanel.add(patientAgeText);

        sexLabel = new JLabel("Sex");
        sexLabel.setBounds(10, 40, 80, 25);
        buttonPanel.add(sexLabel);

        patientSexText = new JTextField(20);
        patientSexText.setBounds(100, 40, 160, 25);
        buttonPanel.add(patientSexText);

        conditionLabel = new JLabel("Medical History");
        conditionLabel.setBounds(10,40,80,25);
        buttonPanel.add(conditionLabel);

        patientConditionText = new JTextField(20);
        patientConditionText.setBounds(100, 40, 160, 25);
        buttonPanel.add(patientConditionText);

        nameBox = new JCheckBox("Patient Name");
        patientBox = new JCheckBox("Patient ID");
        birthDateBox = new JCheckBox("Date of Birth");
        ailmentBox = new JCheckBox("Ailment");
        contactBox = new JCheckBox("Contact Details");


        JPanel tickBoxPanel = new JPanel();
        tickBoxPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Return Fields",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        mainPanel.add(tickBoxPanel);
        tickBoxPanel.setLayout(new FlowLayout());

        tickBoxPanel.add(nameBox);
        tickBoxPanel.add(patientBox);
        tickBoxPanel.add(birthDateBox);
        tickBoxPanel.add(ailmentBox);
        tickBoxPanel.add(contactBox);


        JButton searchButton = new JButton("Search");
        searchButton.setBounds(110, 80, 80, 25);
        searchButton.addActionListener(new SearchListener(nameBox, patientBox, birthDateBox, ailmentBox, contactBox, patientAgeText, patientConditionText, patientSexText));
        mainPanel.add(searchButton);

        patientFrame.setVisible(true);
    }


    // Listener for the search button: will delete the entire frame and re-initialise the entire frame with the
    // updated values in the table
    private static class SearchListener implements ActionListener {
        private JCheckBox nameBox;
        private JCheckBox patientBox;
        private JCheckBox birthDateBox;
        private JCheckBox ailmentBox;
        private JCheckBox contactBox;
        private JTextField patientAgeText;
        private JTextField patientConditionText;
        private JTextField patientSexText;

        public SearchListener(JCheckBox nameBox, JCheckBox patientBox, JCheckBox birthDateBox, JCheckBox ailmentBox, JCheckBox contactBox,
                              JTextField patientAgeText, JTextField patientConditionText, JTextField patientSexText) {
            this.nameBox = nameBox;
            this.patientBox = patientBox;
            this.birthDateBox = birthDateBox;
            this.ailmentBox = ailmentBox;
            this.contactBox = contactBox;
            this.patientAgeText = patientAgeText;
            this.patientConditionText = patientConditionText;
            this.patientSexText = patientSexText;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Patient> patients = Database.getInstance().getPatients();
                Iterator<Patient> iterator = patients.iterator();
                Patient patient = null;
                Object patientData[][] = new Object[patients.size()][6];
                for (int r = 0; r < patients.size(); r++) {
                    patient = iterator.next();
                    if ((!patientAgeText.getText().isEmpty() && patient.getAge() != Integer.parseInt(patientAgeText.getText())) ||
                            (!patientConditionText.getText().isEmpty() && !patient.getMedicalhistory().contains(patientConditionText.getText())) ||
                            (!patientSexText.getText().isEmpty() && !patient.getSex().equals(patientSexText.getText()))) {
                        continue;
                    }
                    if (nameBox.isSelected()) {
                        patientData[r][0] = patient.getName();
                    }
                    if (patientBox.isSelected()) {
                        patientData[r][1] = patient.getUserid();
                    }
                    patientData[r][2] = patient.getSex();
                    if (birthDateBox.isSelected()) {
                        patientData[r][3] = patient.getDateofbirth();
                    }
                    if (ailmentBox.isSelected()) {
                        patientData[r][4] = patient.getMedicalhistory();
                    }
                    if (contactBox.isSelected()) {
                        patientData[r][5] = patient.getPhonenumber();
                    }
                }
                Object patientColumnNames[] = { "PatientName", "PatientID", "Sex", "Date of Birth","Ailment", "Contact Details" } ;
                JTable newTable = new JTable(patientData, patientColumnNames);

                patientFrame.dispose();
                patientFrame = new JFrame();
                new PatientViewFrame(newTable);
            } catch(SQLException er) {
                JOptionPane.showMessageDialog(null, "Unable to render patients.");
                er.printStackTrace();
            }
            catch (NumberFormatException e2){
                JOptionPane.showMessageDialog(null, "Invalid age format.");
                e2.printStackTrace();
            }
        }
    }
}
