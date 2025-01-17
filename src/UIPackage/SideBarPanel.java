package UIPackage;

import UIPackage.Elements.TitleLabel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SideBarPanel extends JPanel {

    public SideBarPanel(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300, 0));
        this.setBackground(Color.LIGHT_GRAY);

        this.add(new TitleLabel("Inserimento prenotazione"), BorderLayout.NORTH);
        this.add(new OptionsPanel(), BorderLayout.CENTER);
        this.add(new JButton("Prenota"), BorderLayout.SOUTH);
    }
}
