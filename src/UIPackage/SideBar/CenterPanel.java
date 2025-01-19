package UIPackage.SideBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CenterPanel extends JPanel {
    private String[] tipologiaAula = {"Aula didattica", "Laboratorio"};
    private String[] codiceAula = {"Didattica 1", "Didattica 2"};
    private String[] data = {"Data"};
    private String[] oraInizio = {"09:00"};
    private String[] oraFine = {"13:00"};

    public CenterPanel(){
        super();
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10,10,10,10));

        JTextField nomeField = new JTextField("Nome");
        nomeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        nomeField.setMargin(new Insets(5, 5, 5, 5));
        this.add(nomeField);
        this.add(Box.createVerticalStrut(10));
        this.add(new JComboBox<String>(tipologiaAula));
        this.add(Box.createVerticalStrut(10));
        this.add(new JComboBox<String>(codiceAula));
        this.add(Box.createVerticalStrut(10));
        this.add(new JComboBox<String>(data));
        this.add(Box.createVerticalStrut(10));
        JPanel orePanel = new JPanel();
        orePanel.setLayout(new GridLayout(1,2,5,0));
        orePanel.setBackground(Color.DARK_GRAY);
        orePanel.add(new JComboBox<String>(oraInizio));
        orePanel.add(new JComboBox<String>(oraFine));
        this.add(orePanel);
        this.add(Box.createVerticalStrut(10));
        JTextArea motivazioneArea = new JTextArea("Motivazione prenotazione");
        motivazioneArea.setMargin(new Insets(5, 5, 5, 5));
        this.add(motivazioneArea);
    }
}
