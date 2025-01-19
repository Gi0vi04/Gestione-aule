package UIPackage.SideBar;


import javax.swing.*;
import java.awt.*;

public class SideBarPanel extends JPanel {

    public SideBarPanel(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300, 0));
        this.setBackground(Color.LIGHT_GRAY);

        this.add(new NorthPanel(), BorderLayout.NORTH);
        this.add(new CenterPanel(), BorderLayout.CENTER);
        this.add(new SouthPanel(), BorderLayout.SOUTH);

    }
}
