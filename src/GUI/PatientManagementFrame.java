import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by AddisonSasko on 2016-11-12.
 */
public class PatientManagementFrame {
    
    /*
     Static variables for table
     */
    private static int WIDTH = 1000;
    private static int HEIGHT = 700;
    
    /*
     Text fields and labels for adding patients
     */
    
    private static JTextField patientNameText;
    private static JTextField patientUserNameText;
    private static JTextField passwordText;
    private static JTextField phoneNumberText;
    private static JTextField patientAgeText;
    private static JTextField patientSexText;
    private static JTextField patientConditionText;
    private static JLabel nameLabel;
    private static JLabel userNameLabel;
    private static JLabel passwordLabel;
    private static JLabel phoneNumberLabel;
    private static JLabel ageLabel;
    private static JLabel sexLabel;
    private static JLabel conditionLabel;
    private static JButton addButton;
    
    /*
     Text fields and labels for deleting patients
     */
    private static JTextField patientDeleteNameText;
    private static JTextField patientDeleteUserNameText;
    private static JLabel nameDeleteLabel;
    private static JLabel userDeleteNameLabel;
    private static JButton removeButton;
    
    
    
    /*
     Graphical elements like the frame and panels necessary for the GUI
     */
    static JFrame patientManagementFrame;
    JPanel mainPanel;
    JPanel patientPanel;
    JPanel patientManipulationPanel;
    JPanel patientAddPanel;
    JPanel patientDeletePanel;
    
    public PatientManagementFrame(JTable Table){
        patientManagementFrame = new JFrame("Patient Management");
        patientManagementFrame.setSize(WIDTH,HEIGHT);
        patientManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        patientManagementFrame.setLayout(new FlowLayout());
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        patientManagementFrame.add(mainPanel);
        
        patientPanel = new JPanel();
        patientPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                                                                  "Patients",
                                                                  TitledBorder.CENTER,
                                                                  TitledBorder.TOP));
        mainPanel.add(patientPanel);
        JScrollPane patientScrollPlane = new JScrollPane(Table);
        patientPanel.add(patientScrollPlane, BorderLayout.CENTER);
        
        patientManipulationPanel = new JPanel();
        patientManipulationPanel.setLayout(new BoxLayout(patientManipulationPanel, BoxLayout.X_AXIS));
        mainPanel.add(patientManipulationPanel);
        
        
        
        patientAddPanel = new JPanel();
        patientAddPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                                                                     "Add Patient",
                                                                     TitledBorder.CENTER,
                                                                     TitledBorder.TOP));
        patientAddPanel.setLayout(new BoxLayout(patientAddPanel, BoxLayout.Y_AXIS));
        
        
        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10, 10, 80, 25);
        patientAddPanel.add(ageLabel);
        
        
        patientAgeText = new JTextField(20);
        patientAgeText.setBounds(100, 10, 50, 25);
        patientAddPanel.add(patientAgeText);
        
        
        sexLabel = new JLabel("Sex:");
        sexLabel.setBounds(10, 40, 80, 25);
        patientAddPanel.add(sexLabel);
        
        
        patientSexText = new JTextField(20);
        patientSexText.setBounds(100, 40, 50, 25);
        patientAddPanel.add(patientSexText);
        
        
        conditionLabel = new JLabel("Condition:");
        conditionLabel.setBounds(10,40,80,25);
        patientAddPanel.add(conditionLabel);
        
        patientConditionText = new JTextField(20);
        patientConditionText.setBounds(100, 40, 50, 25);
        patientAddPanel.add(patientConditionText);
        
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10,40,80,25);
        patientAddPanel.add(nameLabel);
        
        patientNameText = new JTextField(20);
        patientNameText.setBounds(100, 40, 50, 25);
        patientAddPanel.add(patientNameText);
        
        userNameLabel = new JLabel("User Name:");
        userNameLabel.setBounds(10,40,80,25);
        patientAddPanel.add(userNameLabel);
        
        patientUserNameText = new JTextField(20);
        patientUserNameText.setBounds(100, 40, 50, 25);
        patientAddPanel.add(patientUserNameText);
        
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,40,80,25);
        patientAddPanel.add(passwordLabel);
        
        passwordText = new JTextField(20);
        passwordText.setBounds(100, 40, 50, 25);
        patientAddPanel.add(passwordText);
        
        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(10,40,80,25);
        patientAddPanel.add(phoneNumberLabel);
        
        phoneNumberText = new JTextField(20);
        phoneNumberText.setBounds(100, 40, 50, 25);
        patientAddPanel.add(phoneNumberText);
        
        addButton = new JButton();
        addButton.setText("Add Patient");
        patientAddPanel.add(addButton);
        
        
        patientManipulationPanel.add(patientAddPanel);
        
        patientDeletePanel = new JPanel();
        patientDeletePanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                                                                        "Remove Patient",
                                                                        TitledBorder.CENTER,
                                                                        TitledBorder.TOP));
        patientDeletePanel.setLayout(new BoxLayout(patientDeletePanel, BoxLayout.Y_AXIS));
        
        nameDeleteLabel = new JLabel("Patient Name:");
        nameDeleteLabel.setBounds(10,40,80,25);
        patientDeletePanel.add(nameDeleteLabel);
        
        patientDeleteNameText = new JTextField(20);
        patientDeleteNameText.setBounds(100, 40, 50, 25);
        patientDeletePanel.add(patientDeleteNameText);
        
        userDeleteNameLabel = new JLabel("Patient Username:");
        userDeleteNameLabel.setBounds(100, 40, 50, 25);
        patientDeletePanel.add(userDeleteNameLabel);
        
        patientDeleteUserNameText = new JTextField(20);
        patientDeleteUserNameText.setBounds(100, 40, 50, 25);
        patientDeletePanel.add(patientDeleteUserNameText);
        
        removeButton = new JButton();
        removeButton.setText("Remove Patient");
        patientDeletePanel.add(removeButton);
        
        patientManipulationPanel.add(patientDeletePanel);
        
        patientManagementFrame.setVisible(true);
    }
}
