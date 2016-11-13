package GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by AddisonSasko on 2016-11-12.
 */
public class PatientMonitoringFrame {

    private static int WIDTH = 1000;
    private static int HEIGHT = 650;

    static JFrame monitoringFrame;
    static JPanel mainPanel;
    static JPanel buttonPanel;
    static JPanel addMonitorsRelationPanel;
    static JPanel removeMonitorsRelationPanel;
    static JTable patientTable;
    static JTable monitoringTable;


    /*
    Buttons, text fields and labels for removing a monitors relation
     */
    private static JTextField patientID;
    private static JLabel relationDeleteLabel;
    private static JButton removeButton;

    /*
    Buttons, text fields and labels for adding a new monitors relation
    */
    private static JTextField patientNameText;
    private static JTextField noteText;
    private static JLabel nameLabel;
    private static JLabel noteLabel;
    private static JButton addButton;


    public PatientMonitoringFrame(){

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


    }

}


