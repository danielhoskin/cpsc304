package GUI;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by AddisonSasko on 2016-11-13.
 */
public class MedicalTable {

    private static int WIDTH = 800;
    private static int HEIGHT = 600;


    static JFrame tableFrame;
    JPanel mainPanel;
    JTable tableToRender;

    public MedicalTable(JTable toRender){
        tableFrame = new JFrame();
        tableFrame.setSize(WIDTH,HEIGHT);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setLayout(new FlowLayout());
        tableFrame.setVisible(true);

        mainPanel = new JPanel();
        tableFrame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        RenderTables(toRender);

    }

    private void RenderTables(JTable toRender){
        JPanel diagnosisPanel = new JPanel();
        diagnosisPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Result",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        this.tableToRender = toRender;
        JScrollPane diagnosisScrollPane = new JScrollPane(this.tableToRender);
        diagnosisPanel.add(diagnosisScrollPane, BorderLayout.CENTER);
        mainPanel.add(diagnosisPanel);

    }

}
