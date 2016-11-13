package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by AddisonSasko on 2016-11-11.
 */
public class MainFrameListener implements ActionListener {

    public String name;

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "You Pressed the " + name + " button.");
    }

    public void setName(String name){
        this.name = name;
    }
}
