package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;

    private static JTextField userText;
    private static JTextField passwordText;
    private static JLabel passwordLabel;
    private static JLabel userLabel;

    // Temporary main function to facilitate testing
    public static void main(String[] args) {
        initialiseComponents();

        }

    /*
    // Helper: Work in progress. 
    public LoginFrame(){
        JFrame frame = new JFrame("Harambe Inc.");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
        initialiseComponents(panel);
    }
    */

    private static void initialiseComponents() {

        // Initialises the Frame (a Frame is a top-level window with a title and a border) for the login screen, with
        // associated fields and name.
        JFrame frame = new JFrame("Harambe Inc.");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // Initialises the Panel (JPanel is a generic lightweight container) that serves as the content plane for the
        // applications frame. The API is lightweight and most operations invoked will be inherited.
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);


        // Initialises all of the Labels and Textfields in the frame, which can display an area for a short text string and
        // and allow for the editing of a single line of text, respectively.

        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(110, 80, 80, 25);
        panel.add(loginButton);

        ActionListener NewLoginListener = new NewLoginListener();
        loginButton.addActionListener(NewLoginListener);
        frame.setVisible(true);

    }

    // Inner-Class of LoginScreen: listens for actions that are performed on the login button, then returns a value based
    // on the user provided input.
    private static class NewLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String givenUsername = userText.getText();
            String givenPassword = passwordText.getText();

            if (givenUsername.equals("John") && givenPassword.equals("123")){
                JOptionPane.showMessageDialog(null, "Login Successfully Attempted");
                return;
            }

            else
                JOptionPane.showMessageDialog(null, "Username or password is invalid, please try again.");
                userText.setText("");
                passwordText.setText("");
        }
    }

}



