package UIPackage.Elements;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {
    public TitleLabel(String title){
        super(title);
        Font font = new Font("Helvetica", Font.BOLD,16);
        this.setFont(font);
    }
}
