package UIPackage.Tabella.NuovaPrenotazione;

import LogicaPackage.Aula;
import LogicaPackage.Prenotazione;
import LogicaPackage.Utils.Costanti;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class NuovaPrenotazione extends JFrame {
    private final JTextField nomeTextField;
    private final JComboBox<Aula> aulaComboBox;
    private final JComboBox<String> motivazioneComboBox;
    private final JSpinner dateSpinner;
    private final JComboBox<String> oraInizioCombo;
    private final JComboBox<String> oraFineCombo;
    private final JButton buttonConferma;

    public NuovaPrenotazione(int row, int column, PrenotazioneListener prenotazioneListener, LocalDate currentDate){
        super("Nuova prenotazione");
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        // Sezione inserimento "Nome prenotante"
        JPanel nomePanel = new JPanel();
        nomePanel.setLayout(new BoxLayout(nomePanel, BoxLayout.X_AXIS));
        nomeTextField = new JTextField(20);
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

        // Sezione selezione "Aula"
        JPanel selezioneAulaPanel = new JPanel(new BorderLayout());
        aulaComboBox = new JComboBox<>(Costanti.AULE);

        aulaComboBox.setSelectedIndex(column - 1);

        selezioneAulaPanel.add(aulaComboBox, BorderLayout.CENTER);

        // Sezione inserimento "Motivazione"
        JPanel motivazionePanel = new JPanel();
        motivazionePanel.setLayout(new BoxLayout(motivazionePanel, BoxLayout.X_AXIS));
        motivazioneComboBox = new JComboBox<>(Costanti.MOTIVAZIONI);

        motivazionePanel.add(motivazioneComboBox);

        // Sezione inserimento "Data"
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();

        dateSpinner = new JSpinner(new SpinnerDateModel(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), startDate, endDate, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        // Sezione inserimento "Orario"
        JPanel orarioPanel = new JPanel();
        orarioPanel.setLayout(new BoxLayout(orarioPanel, BoxLayout.X_AXIS));
        oraInizioCombo = new JComboBox<>(Arrays.copyOfRange(Costanti.ORARI_AMMESSI, 0, Costanti.ORARI_AMMESSI.length - 1));
        oraFineCombo = new JComboBox<>(Costanti.ORARI_AMMESSI);

        oraInizioCombo.addActionListener(e -> aggiornaOrarioFineAmmesso());
        oraInizioCombo.setSelectedIndex(row);

        orarioPanel.add(oraInizioCombo);
        orarioPanel.add(oraFineCombo);

        //Sezione CTA
        JPanel panelCTA = new JPanel(new BorderLayout());
        buttonConferma = new JButton("Conferma");
        JButton buttonAnnulla = new JButton("Annulla");
        buttonAnnulla.addActionListener(e -> dispose());

        buttonConferma.setEnabled(false);
        buttonConferma.addActionListener(e -> addPrenotazione(prenotazioneListener));
        panelCTA.add(buttonAnnulla, BorderLayout.WEST);
        panelCTA.add(buttonConferma, BorderLayout.EAST);

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
        super.setLocationRelativeTo(null);
    }

    private void addPrenotazione(PrenotazioneListener prenotazioneListener) {
        Date dateSelected = (Date) dateSpinner.getValue();
        LocalDate localDateSelected = dateSelected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Prenotazione prenotazione = new Prenotazione((Aula) aulaComboBox.getSelectedItem(),
                localDateSelected,
                oraInizioCombo.getSelectedIndex(),
                oraInizioCombo.getSelectedIndex() + oraFineCombo.getSelectedIndex() + 1,
                nomeTextField.getText(),
                (String) motivazioneComboBox.getSelectedItem());
        prenotazioneListener.addPrenotazione(prenotazione);

        dispose();
    }

    private void aggiornaOrarioFineAmmesso(){
        int oraInizioSelezionato = oraInizioCombo.getSelectedIndex();

        oraFineCombo.removeAllItems();
        for(int i = oraInizioSelezionato + 1; i < Costanti.ORARI_AMMESSI.length; i++) oraFineCombo.addItem(Costanti.ORARI_AMMESSI[i]);

        oraFineCombo.setSelectedIndex(0);
    }

    private void checkValidity(){
        boolean validity = !nomeTextField.getText().isEmpty();
        buttonConferma.setEnabled(validity);
    }
}
