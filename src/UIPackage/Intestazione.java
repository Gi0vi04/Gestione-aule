package UIPackage;

import LogicaPackage.Utils.FileIO;
import UIPackage.Tabella.TabellaAule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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

        add(createSelectDatePanel(), BorderLayout.WEST);
        add(createCTAPanel(),BorderLayout.EAST);
    }

    /**
     * Crea il pannello contenente i tre pulsanti per interagire con le prenotazioni della tabella
     * @return
     */
    private JPanel createCTAPanel() {
        JPanel ctaPanel = new JPanel();
        ctaPanel.setBackground(Color.LIGHT_GRAY);
        JButton saveButton = new JButton("Salva prenotazioni");
        JButton loadButton = new JButton("Carica prenotazioni");
        JButton printButton = new JButton("Stampa prenotazioni");

        saveButton.addActionListener(e -> FileIO.savePrenotazioni(tabellaAule.getPrenotazioni()));
        loadButton.addActionListener(e -> FileIO.loadPrenotazioni(tabellaAule));
        printButton.addActionListener(e -> FileIO.printPrenotazioni(tabellaAule.getTable()));

        ctaPanel.add(printButton);
        ctaPanel.add(saveButton);
        ctaPanel.add(loadButton);
        return ctaPanel;
    }

    /**
     * Crea il pannello contenente il selettore della data per modificare le prenotazioni visualizzate nella tabella
     * @return
     */
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
            tabellaAule.setCurrentDate(localDateSelected);
        });
        
        datePanel.add(new JLabel("Data:"));
        datePanel.add(dateSpinner);
        return datePanel;
    }
}
