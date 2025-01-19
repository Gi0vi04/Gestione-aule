package UIPackage.Tabella;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class NuovaPrenotazioneFrame extends JFrame {
    private final static String[] tipologiaAula = {"Aula didattica", "Laboratorio"};
    private final static String[] orarioAmmesso = {
            "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
            "16:00", "17:00", "18:00"
    };
    private final static String[] motivazioni = {"Lezione", "Esame", "Tutorato", "Esercitazione", "Lezione di recupero"};

    public NuovaPrenotazioneFrame(int row, int column, String currentValue){
        super("Nuova prenotazione");
        this.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //Sezione nome prenotante
        JPanel nomePanel = new JPanel();

        JLabel nomeLabel = new JLabel("Nome");
        JTextField nomeTextField = new JTextField(20);

        nomePanel.add(nomeLabel);
        nomePanel.add(nomeTextField);

        //Sezione selezione aula
        JPanel selezioneAulaPanel = new JPanel();

        JComboBox<String> tipologiaAulaComboBox = new JComboBox<>(tipologiaAula);
        JComboBox<String> aulaComboBox = new JComboBox<>(tipologiaAula);

        selezioneAulaPanel.add(tipologiaAulaComboBox);
        selezioneAulaPanel.add(aulaComboBox);

        //Sezione data e orario
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        JPanel dataPanel = new JPanel();
        UtilDateModel model = new UtilDateModel();
        model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);

        Properties p = new Properties();
        p.put("text.today", "Oggi");
        p.put("text.month", "Mese");
        p.put("text.year", "Anno");
        p.put("text.day", "Giorno");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        dataPanel.add(new JLabel("Data"));
        dataPanel.add(datePicker);

        //Sezione orario
        JPanel orarioPanel = new JPanel();
        JComboBox<String> oraInizioComboBox = new JComboBox<>(orarioAmmesso);
        JComboBox<String> oraFineComboBox = new JComboBox<>(orarioAmmesso);
        orarioPanel.add(new JLabel("Inizio"));
        orarioPanel.add(oraInizioComboBox);
        orarioPanel.add(new JLabel("Fine"));
        orarioPanel.add(oraFineComboBox);

        //Sezione motivazione
        JPanel motivazionePanel = new JPanel();
        JComboBox<String> motivazioneComboBox = new JComboBox<>(motivazioni);
        motivazionePanel.add(new JLabel("Motivazione"));
        motivazionePanel.add(motivazioneComboBox);

        //Sezione CTA
        JPanel panelCTA = new JPanel();
        JButton buttonConferma = new JButton("Conferma");
        JButton buttonAnnulla = new JButton("Annulla");
        panelCTA.add(buttonAnnulla);
        panelCTA.add(buttonConferma);

        //Inserisco tutto nel pannello principale e infine al frame
        mainPanel.add(nomePanel);
        mainPanel.add(selezioneAulaPanel);
        mainPanel.add(dataPanel);
        mainPanel.add(orarioPanel);
        mainPanel.add(motivazionePanel);
        mainPanel.add(panelCTA);
        this.add(mainPanel);
        this.pack();
    }
}
