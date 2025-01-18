package UIPackage.SideBar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BottomPanel extends JPanel {
    public BottomPanel(){
        super();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(0,50));
        this.setBackground(Color.YELLOW);


        this.add(new JButton("Reimposta"), BorderLayout.WEST);
        this.add(new JButton("Prenota"), BorderLayout.EAST);
    }
}
