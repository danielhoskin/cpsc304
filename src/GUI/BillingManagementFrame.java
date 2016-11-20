package GUI;

import database.Database;
import lib.Pair;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by AddisonSasko on 2016-11-13.
 */
public class BillingManagementFrame {


    private static int WIDTH = 800;
    private static int HEIGHT = 600;

    /*
   Panels and Tables for rendering the frame
   */
    static JFrame billingManagementFrame;
    JPanel mainPanel;
    JTable billingTable;
    JPanel billingManipulationPanel;
    JPanel billingInterestingPanel;


    /*
    Buttons and listeners for the Frame.
     */
    private static JTextField billIDText;
    private static JTextField amountText;
    private static JLabel billIDLabel;
    private static JLabel amountLabel;
    private static JButton submitButton;


    private static JButton arrangeBySize;


    // Constructor for the ActivityManagementFrame
    public BillingManagementFrame(JTable billingTable) {
        billingManagementFrame = new JFrame();
        billingManagementFrame.setSize(WIDTH,HEIGHT);
        billingManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billingManagementFrame.setLayout(new FlowLayout());
        billingManagementFrame.setVisible(true);

        mainPanel = new JPanel();
        billingManagementFrame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        RenderTable(billingTable);
        RenderInteractiveSpace();
    }


    // Renders the tables for the frame
    private void RenderTable(JTable billingTable){
        JPanel billingPanel = new JPanel();
        billingPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Current Bills",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.billingTable = billingTable;
        JScrollPane diagnosisScrollPane = new JScrollPane(this.billingTable);
        billingPanel.add(diagnosisScrollPane, BorderLayout.CENTER);
        mainPanel.add(billingPanel);

    }

    // Renders the interactive space for the frame: buttons, texts and tags
    private void RenderInteractiveSpace(){
        billingManipulationPanel = new JPanel();
        billingManipulationPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Update Bill",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        billingManipulationPanel.setLayout(new FlowLayout());
        billingManagementFrame.add(billingManipulationPanel);

        billIDLabel = new JLabel("Bill ID:");
        billIDLabel.setBounds(10,40,80,25);
        billingManipulationPanel.add(billIDLabel);
        billIDText = new JTextField(10);
        billIDText.setBounds(100, 40, 50, 25);
        billingManipulationPanel.add(billIDText);

        amountLabel = new JLabel("Amount Paid:");
        amountLabel.setBounds(10,40,80,25);
        billingManipulationPanel.add(amountLabel);
        amountText = new JTextField(10);
        amountText.setBounds(100, 40, 50, 25);
        billingManipulationPanel.add(amountText);


        submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener(new BillButtonListener(billingTable));
        billingManipulationPanel.add(submitButton);


        billingInterestingPanel = new JPanel();
        billingInterestingPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Bill Averages By Doctor",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        billingInterestingPanel.setLayout(new FlowLayout());
        billingManagementFrame.add(billingInterestingPanel);

        arrangeBySize = new JButton();
        arrangeBySize.setText("Submit");
        arrangeBySize.addActionListener(new BillByDoctorListener());
        billingInterestingPanel.add(arrangeBySize);

    }


    // Listener for Bill, queries the database and refreshes the table page if it is successful
    private static class BillButtonListener implements ActionListener {
        private JTable billingTable;

        public BillButtonListener(JTable billingTable) {
            this.billingTable = billingTable;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String givenBillID = billIDText.getText();
            String givenBillAmount = amountText.getText();

            if (givenBillID.equals("") && givenBillAmount.equals("")){
                final ImageIcon icon = new ImageIcon("/Users/AddisonSasko/Desktop/comfy.jpg");
                JOptionPane.showMessageDialog(null, "Please provide a valid Billing ID and a valid amount", "Error", JOptionPane.INFORMATION_MESSAGE ,icon);
                return;
            }
            if (givenBillID.equals("")){
                final ImageIcon icon = new ImageIcon("/Users/AddisonSasko/Desktop/comfy.jpg");
                JOptionPane.showMessageDialog(null, "Please provide a valid Billing ID", "Error", JOptionPane.INFORMATION_MESSAGE ,icon);
                return;
            }
            if (givenBillAmount.equals("")){
                final ImageIcon icon = new ImageIcon("/Users/AddisonSasko/Desktop/comfy.jpg");
                JOptionPane.showMessageDialog(null, "Please provide a valid amount", "Error", JOptionPane.INFORMATION_MESSAGE ,icon);
                return;
            }

           try {
               if (Database.getInstance().updateAmountPaid(Integer.parseInt(givenBillID), Float.parseFloat(givenBillAmount))) {
                   // TODO: refresh bills after updating amount paid
                   // TODO: Joseph pls. Something like this -- but it's not working correctly
//                   billingManagementFrame.dispose();
//                   billingManagementFrame = new JFrame();
//                   new BillingManagementFrame(billingTable);
               } else {
                   JOptionPane.showMessageDialog(null, "Unable to update bill: " + givenBillID, "Error", JOptionPane.INFORMATION_MESSAGE);
               }
           } catch(SQLException err) {

           }
        }
    }

    private static class BillByDoctorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Pair<Integer, Float> pair = Database.getInstance().getDoctorWithMaximumAverageOperationCost();
                Object doctorData[][] = new Object[1][2];
                doctorData[0][0] = pair.getLeft();
                doctorData[0][1] = pair.getRight();
                Object billColumnNames[] = { "Doctor ID", "Maximum Average Operation Cost"} ;
                JTable billTable = new JTable(doctorData, billColumnNames);
                MedicalTable newTable = new MedicalTable(billTable);
            } catch(SQLException er) {
                JOptionPane.showMessageDialog(null, "Unable to render maximum average operation cost.");
                er.printStackTrace();
            }
        }
    }
}
