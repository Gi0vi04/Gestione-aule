package UIPackage.InformazioniLaterali;

import UIPackage.Tabella.TabellaAule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Intestazione extends JPanel {
    TabellaAule tabellaAule;

    public Intestazione(TabellaAule tabellaAule){
        this.tabellaAule = tabellaAule;
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setBorder(new EmptyBorder(5,5,5,5));

        // Sezione stampa
        JPanel printPanel = new JPanel();
        printPanel.setBackground(Color.LIGHT_GRAY);
        JButton printButton = new JButton("Stampa prenotazioni");
        printButton.addActionListener(e -> tabellaAule.stampaPrenotazioni());
        printPanel.add(printButton);

        // Sezione modifica data
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        datePanel.setBackground(Color.LIGHT_GRAY);

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 2);
        Date endDate = calendar.getTime();

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(today, startDate, endDate, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.addChangeListener(e -> {
            Date dateSelected = (Date) dateSpinner.getValue();
            LocalDate localDateSelected = dateSelected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            tabellaAule.setCurrentDate(localDateSelected);
        });
        datePanel.add(dateSpinner);

        // Sezione CTA
        JPanel ctaPanel = new JPanel();
        ctaPanel.setBackground(Color.LIGHT_GRAY);
        JButton saveButton = new JButton("Salva");
        JButton loadButton = new JButton("Carica");

        saveButton.addActionListener(e -> tabellaAule.salvaPrenotazioni());
        loadButton.addActionListener(e -> tabellaAule.caricaPrenotazioni());

        ctaPanel.add(saveButton);
        ctaPanel.add(loadButton);

        add(printPanel, BorderLayout.WEST);
        add(datePanel, BorderLayout.CENTER);
        add(ctaPanel,BorderLayout.EAST);
    }
}
