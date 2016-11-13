package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;

    static JFrame frame;

    private static JTextField userText;
    private static JTextField passwordText;
    private static JLabel passwordLabel;
    private static JLabel userLabel;


    public LoginScreen(){
        initialiseComponents();
    }


    private static void initialiseComponents() {

        frame = new JFrame("Harambase Inc.");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

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
        loginButton.addActionListener(new NewLoginListener());
        panel.add(loginButton);

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
                MainFrame primaryFrame = new MainFrame(new User("John", 3));
                frame.dispose();
            }

            else
                JOptionPane.showMessageDialog(null, "Username or password is invalid, please try again.");
                userText.setText("");
                passwordText.setText("");
        }
    }

}



