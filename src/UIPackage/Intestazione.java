package UIPackage;

import LogicaPackage.Utils.FileIO;
import UIPackage.Tabella.TabellaPrenotazioni;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe destinata a realizzare l'interfaccia dell'intestazione del software
 */
public class Intestazione extends JPanel {
    /**
     * Variabile che contiene il riferimento alla tabella delle prenotazioni
     */
    TabellaPrenotazioni tabellaPrenotazioni;

    /**
     * Costruttore dell'interfaccia grafica dell'intestazioe del software
     * @param tabellaPrenotazioni tabella contenente le prenotazioni
     */
    public Intestazione(TabellaPrenotazioni tabellaPrenotazioni){
        this.tabellaPrenotazioni = tabellaPrenotazioni;
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setBorder(new EmptyBorder(5,5,5,5));

        add(createSelectDatePanel(), BorderLayout.WEST);
        add(createCTAPanel(),BorderLayout.EAST);
    }

    private JPanel createCTAPanel() {
        JPanel ctaPanel = new JPanel();
        ctaPanel.setBackground(Color.LIGHT_GRAY);
        JButton saveButton = new JButton("Salva prenotazioni");
        JButton loadButton = new JButton("Carica prenotazioni");
        JButton printButton = new JButton("Stampa prenotazioni");

        saveButton.addActionListener(e -> FileIO.savePrenotazioni(tabellaPrenotazioni.getPrenotazioni()));
        loadButton.addActionListener(e -> FileIO.loadPrenotazioni(tabellaPrenotazioni));
        printButton.addActionListener(e -> FileIO.printPrenotazioni(tabellaPrenotazioni.getTable()));

        ctaPanel.add(printButton);
        ctaPanel.add(saveButton);
        ctaPanel.add(loadButton);
        return ctaPanel;
    }

    private JPanel createSelectDatePanel(){
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
            tabellaPrenotazioni.setSelectedDate(localDateSelected);
        });
        
        datePanel.add(new JLabel("Data:"));
        datePanel.add(dateSpinner);
        return datePanel;
    }
}
