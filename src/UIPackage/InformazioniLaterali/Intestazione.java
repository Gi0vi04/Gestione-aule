package UIPackage.InformazioniLaterali;

import UIPackage.Tabella.TabellaAule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Intestazione extends JPanel {
    TabellaAule tabellaAule;

    public Intestazione(TabellaAule tabellaAule){
        this.tabellaAule = tabellaAule;

        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);
        setBorder(new EmptyBorder(5,5,5,5));

        //Sezione modifica data
//        JPanel editDatePanel = new JPanel();
//        editDatePanel.setBackground(Color.DARK_GRAY);
//        JLabel dataVisualizzata = new JLabel("Data visualizzata: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
//        dataVisualizzata.setForeground(Color.WHITE);
//        editDatePanel.add(dataVisualizzata);

        //Sezione CTA
        JPanel ctaPanel = new JPanel();
        ctaPanel.setBackground(Color.DARK_GRAY);
        JButton saveButton = new JButton("Salva");
        saveButton.addActionListener(e -> tabellaAule.salvaPrenotazioni());
        JButton loadButton = new JButton("Carica");
        loadButton.addActionListener(e -> tabellaAule.caricaPrenotazioni());

        ctaPanel.add(saveButton);
        ctaPanel.add(loadButton);

        add(ctaPanel,BorderLayout.EAST);
    }
}
