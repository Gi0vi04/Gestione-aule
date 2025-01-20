package UIPackage.Tabella.NuovaPrenotazione;

import LogicaPackage.COSTANTI;
import LogicaPackage.Prenotazione;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class NuovaPrenotazione extends JFrame implements ActionListener {
    private final JComboBox<String> oraInizioCombo = new JComboBox<>(COSTANTI.orariAmmessi);
    private JComboBox<String> oraFineCombo;
    private JButton buttonConferma = new JButton("Conferma");
    private JTextField nomeTextField = new JTextField(20);

    public NuovaPrenotazione(int row, int column, NuovaPrenotazioneListener nuovaPrenotazioneListener){
        super("Nuova prenotazione");
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        //Sezione nome prenotante
        JPanel nomePanel = new JPanel();
        nomePanel.setLayout(new BoxLayout(nomePanel, BoxLayout.X_AXIS));

        nomeTextField.setFont(new Font("Dialog", Font.BOLD, 12));
        nomeTextField.setMargin(new Insets(2,2,2,2));
        nomeTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkValidity();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkValidity();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkValidity();
            }
        });

        nomePanel.add(new JLabel("Nome*"));
        nomePanel.add(Box.createHorizontalStrut(10));
        nomePanel.add(nomeTextField);

        //Sezione selezione aula
        JPanel selezioneAulaPanel = new JPanel(new BorderLayout());
        JComboBox<String> aulaComboBox = new JComboBox<>(COSTANTI.aule);
        aulaComboBox.setSelectedIndex(column);
        selezioneAulaPanel.add(aulaComboBox, BorderLayout.CENTER);

        //Sezione motivazione
        JPanel motivazionePanel = new JPanel();
        motivazionePanel.setLayout(new BoxLayout(motivazionePanel, BoxLayout.X_AXIS));
        JComboBox<String> motivazioneComboBox = new JComboBox<>(COSTANTI.motivazioni);
        motivazionePanel.add(motivazioneComboBox);

        //Sezione data
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(today,today,endDate,Calendar.MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        //Sezione orario
        JPanel orarioPanel = new JPanel();
        orarioPanel.setLayout(new BoxLayout(orarioPanel, BoxLayout.X_AXIS));

        oraFineCombo = new JComboBox<>(COSTANTI.orariAmmessi);
        oraInizioCombo.addActionListener(e -> aggiornaOrarioFineAmmesso());
        oraInizioCombo.setSelectedIndex(row);

        orarioPanel.add(oraInizioCombo);
        orarioPanel.add(oraFineCombo);

        //Sezione CTA
        JPanel panelCTA = new JPanel(new BorderLayout());
        JButton buttonAnnulla = new JButton("Annulla");
        buttonAnnulla.addActionListener(this);

        buttonConferma.setEnabled(false);
        buttonConferma.addActionListener(e -> {
            Prenotazione prenotazione = new Prenotazione(aulaComboBox.getSelectedIndex(), (Date) dateSpinner.getValue(),
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
        mainPanel.add(dateSpinner);
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

    public void checkValidity(){
        boolean validity = true;

        //Controllo che il nome sia inserito
        if (nomeTextField.getText().isEmpty())
            validity = false;

        buttonConferma.setEnabled(validity);
    }
}
