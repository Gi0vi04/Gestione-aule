package UIPackage;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {
    final String[] tipologiaAula = {"Aula didattica", "Laboratorio"};

    public OptionsPanel(){
        super();
        this.add(new JComboBox<String>(tipologiaAula));
        this.setMinimumSize(this.getPreferredSize());
        this.setPreferredSize(this.getPreferredSize());

        this.setBackground(Color.GRAY);
    }
}
