import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
        
        
        conditionLabel = new JLabel("Condition");
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
        searchButton.addActionListener(new SearchListener());
        mainPanel.add(searchButton);
        
        patientFrame.setVisible(true);
    }
    
    
    // Listener for the search button: will delete the entire frame and re-innitialise the entire frame with the
    // updated values in the table
    private static class SearchListener implements ActionListener {
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Object patientData[][] = {
                
                { "Ritchie","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
                { "Welles","5258932","Male", "18/10/1956", "Cancer :^(" , "(778)-991-9316" },
            };
            Object patientColumnNames[] = { "PatientName", "PatientID", "Sex", "Date of Birth","Ailment", "Contact Details" } ;
            JTable newTable = new JTable(patientData, patientColumnNames);
            
            
            patientFrame.dispose();
            patientFrame = new JFrame();
            new PatientViewFrame(newTable);
            
        }
    }
}
