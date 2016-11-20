package GUI;
import database.Database;
import tables.*;

import javax.print.Doc;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginScreen {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;

    static JFrame frame;

    private static JTextField userIDText;
    private static JTextField passwordText;
    private static JLabel passwordLabel;
    private static JLabel userIDLabel;
    private static Database loginConnection;




    public LoginScreen(){
        initialiseComponents();
    }


    private static void initialiseComponents() {

        loginConnection = Database.getInstance();

        frame = new JFrame("Harambase Inc.");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        userIDLabel = new JLabel("User ID");
        userIDLabel.setBounds(10, 10, 80, 25);
        panel.add(userIDLabel);
        userIDText = new JTextField(20);
        userIDText.setBounds(100, 10, 160, 25);
        panel.add(userIDText);


        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);


        JButton loginButton = new JButton("login");
        loginButton.setBounds(110, 80, 80, 25);
        loginButton.addActionListener(new NewLoginListener());
        panel.add(loginButton);

        frame.setVisible(true);


    }

    // Inner-Class of LoginScreen: listens for actions that are performed on the login button, then returns a value based
    // on the user provided input.
    private static class NewLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String givenUsername = userIDText.getText();
            String givenPassword = passwordText.getText();

            try {
                int userId = Integer.parseInt(givenUsername);
                if (loginConnection.checkUser(userId, givenPassword)){
                    JOptionPane.showMessageDialog(null, "Login Successfully Attempted");
                    if (loginConnection.isPatient(userId)) {
                        Patient newPatient = loginConnection.getPatient(userId);
                        MainFrame primaryFrame = new MainFrame(newPatient);
                        frame.dispose();
                    }
                    else if (loginConnection.isDoctor(userId)){
                        Doctor newDoctor = loginConnection.getDoctor(userId);
                        MainFrame primaryFrame = new MainFrame(newDoctor);
                        frame.dispose();
                    }
                    else if (loginConnection.isNurse(userId)){
                        Nurse newNurse = loginConnection.getNurse(userId);
                        MainFrame primaryFrame = new MainFrame(newNurse);
                        frame.dispose();
                    }
                    else if (loginConnection.isReceptionist(userId)){
                        Receptionist newReceptionist = loginConnection.getReceptionist(userId);
                        MainFrame primaryFrame = new MainFrame(newReceptionist);
                        frame.dispose();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Username or password is invalid, please try again.");
                    userIDText.setText("");
                    passwordText.setText("");
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Username or password is invalid, please try again.");
                userIDText.setText("");
                passwordText.setText("");
                e1.printStackTrace();
            }
            catch (NumberFormatException e2){
                JOptionPane.showMessageDialog(null, "Username or password is invalid, please try again.");
                userIDText.setText("");
                passwordText.setText("");
                e2.printStackTrace();
            }

        }
    }

}



