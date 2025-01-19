package UIPackage.NuovaPrenotazione;

import LogicaPackage.COSTANTI;
import LogicaPackage.Prenotazione;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class NuovaPrenotazione extends JFrame implements ActionListener {
    private JComboBox<String> oraInizioCombo;
    private JComboBox<String> oraFineCombo;

    public NuovaPrenotazione(int row, int column, NuovaPrenotazioneListener nuovaPrenotazioneListener){
        super("Nuova prenotazione");
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        //Sezione nome prenotante
        JPanel nomePanel = new JPanel();
        nomePanel.setLayout(new BoxLayout(nomePanel, BoxLayout.X_AXIS));

        JTextField nomeTextField = new JTextField(20);
        nomeTextField.setMargin(new Insets(2,2,2,2));

        nomePanel.add(new JLabel("Nome"));
        nomePanel.add(Box.createHorizontalStrut(10));
        nomePanel.add(nomeTextField);

        //Sezione selezione aula
        JPanel selezioneAulaPanel = new JPanel(new BorderLayout());
        JComboBox<String> aulaComboBox = new JComboBox<>(COSTANTI.aule);
        aulaComboBox.setSelectedIndex(column - 1);
        selezioneAulaPanel.add(aulaComboBox, BorderLayout.CENTER);

        //Sezione motivazione
        JPanel motivazionePanel = new JPanel();
        motivazionePanel.setLayout(new BoxLayout(motivazionePanel, BoxLayout.X_AXIS));
        JComboBox<String> motivazioneComboBox = new JComboBox<>(COSTANTI.motivazioni);
        motivazionePanel.add(motivazioneComboBox);

        //Sezione data
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        JPanel dataPanel = new JPanel(new BorderLayout());
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
        dataPanel.add(datePicker, BorderLayout.CENTER);

        //Sezione orario
        JPanel orarioPanel = new JPanel();
        orarioPanel.setLayout(new BoxLayout(orarioPanel, BoxLayout.X_AXIS));
        oraInizioCombo = new JComboBox<>(COSTANTI.orariAmmessi);
        oraFineCombo = new JComboBox<>(COSTANTI.orariAmmessi);
        oraInizioCombo.addActionListener(e -> aggiornaOrarioFineAmmesso());
        oraInizioCombo.setSelectedIndex(row);

        orarioPanel.add(oraInizioCombo);
        orarioPanel.add(oraFineCombo);

        //Sezione CTA
        JPanel panelCTA = new JPanel(new BorderLayout());
        JButton buttonConferma = new JButton("Conferma");
        JButton buttonAnnulla = new JButton("Annulla");
        buttonAnnulla.addActionListener(this);
        buttonConferma.addActionListener(e -> {
            Prenotazione prenotazione = new Prenotazione(aulaComboBox.getSelectedIndex() + 1, (Date) datePicker.getModel().getValue(),
                    oraInizioCombo.getSelectedIndex(),
                    oraInizioCombo.getSelectedIndex() + oraFineCombo.getSelectedIndex(),
                    nomeTextField.getText(),
                    (String) motivazioneComboBox.getSelectedItem());
            nuovaPrenotazioneListener.onPrenotazioneAggiunta(prenotazione);
            dispose();
        });
        panelCTA.add(buttonAnnulla, BorderLayout.WEST);
        panelCTA.add(buttonConferma, BorderLayout.CENTER);

        //Inserisco tutto nel pannello principale e infine al frame
        mainPanel.add(nomePanel);
        mainPanel.add(selezioneAulaPanel);
        mainPanel.add(motivazionePanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(dataPanel);
        mainPanel.add(orarioPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(panelCTA);

        add(mainPanel);
        pack();
    }

    private void aggiornaOrarioFineAmmesso(){
        int oraInizioSelezionato = oraInizioCombo.getSelectedIndex();

        oraFineCombo.removeAllItems();
        for(int i = oraInizioSelezionato; i < COSTANTI.orariAmmessi.length; i++) oraFineCombo.addItem(COSTANTI.orariAmmessi[i]);

        oraFineCombo.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
