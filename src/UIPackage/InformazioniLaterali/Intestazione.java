package UIPackage.InformazioniLaterali;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Intestazione extends JPanel {
    public Intestazione(){
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);
        setBorder(new EmptyBorder(5,5,5,5));

        //Sezione modifica data
        JPanel editDatePanel = new JPanel();
        editDatePanel.setBackground(Color.DARK_GRAY);
        JLabel dataVisualizzata = new JLabel("Data visualizzata: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        dataVisualizzata.setForeground(Color.WHITE);
        editDatePanel.add(dataVisualizzata);

        //Sezione CTA
        JPanel ctaPanel = new JPanel(new GridLayout(2,1));
        JButton saveButton = new JButton("Salva");
        JButton loadButton = new JButton("Carica");
        ctaPanel.add(saveButton);
        ctaPanel.add(loadButton);

        add(editDatePanel,BorderLayout.WEST);
        add(ctaPanel,BorderLayout.EAST);
    }
}
