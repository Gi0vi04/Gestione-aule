package UIPackage.SideBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SouthPanel extends JPanel {
    public SouthPanel(){
        super();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(0,50));
        this.setBackground(Color.LIGHT_GRAY);

        this.setBorder(new EmptyBorder(5,10,5,10));
        this.add(new JButton("Reimposta"), BorderLayout.WEST);
        this.add(new JButton("Prenota"), BorderLayout.EAST);
    }
}
