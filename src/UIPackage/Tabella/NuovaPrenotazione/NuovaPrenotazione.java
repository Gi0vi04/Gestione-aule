package UIPackage.Tabella.NuovaPrenotazione;

import LogicaPackage.Aula;
import LogicaPackage.Prenotazione;
import LogicaPackage.Utils.Costanti;
import LogicaPackage.Utils.SimpleDocumentListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class NuovaPrenotazione extends JFrame {
    private JTextField nomeTextField;
    private JComboBox<String> tipologiaComboBox;
    private JComboBox<Aula> aulaComboBox;
    private JComboBox<String> motivazioneComboBox;
    private JSpinner dateSpinner;
    private JComboBox<String> oraInizioCombo;
    private JComboBox<String> oraFineCombo;
    private JButton buttonConferma;

    public static final String AULA_DIDATTICA = "Aula didattica";
    public static final String LABORATORIO = "Laboratorio";

    public NuovaPrenotazione(int row, int column, PrenotazioneListener prenotazioneListener, LocalDate currentDate){
        super("Nuova prenotazione");
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        mainPanel.add(createNomePanel());
        mainPanel.add(createAulaPanel(Costanti.AULE[column - 1]));
        mainPanel.add(createMotivazionePanel());
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createDateSpinner(currentDate));
        mainPanel.add(createOrarioPanel(row));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createCTAPanel(prenotazioneListener));

        filterAule("Aula didattica"); //Da rendere dinamico

        add(mainPanel);
        pack();
        super.setLocationRelativeTo(null);
    }

    private JPanel createCTAPanel(PrenotazioneListener prenotazioneListener) {
        JPanel ctaPanel = new JPanel(new BorderLayout());
        buttonConferma = new JButton("Conferma");
        JButton buttonAnnulla = new JButton("Annulla");
        buttonAnnulla.addActionListener(e -> dispose());

        buttonConferma.setEnabled(false);
        buttonConferma.addActionListener(e -> {
            Date dateSelected = (Date) dateSpinner.getValue();
            LocalDate localDateSelected = dateSelected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime startTime = LocalTime.parse((String) oraInizioCombo.getSelectedItem());
            LocalTime endTime = LocalTime.parse((String) oraFineCombo.getSelectedItem());

            Prenotazione prenotazione = new Prenotazione((Aula) aulaComboBox.getSelectedItem(),
                    localDateSelected,
                    startTime,
                    endTime,
                    nomeTextField.getText(),
                    (String) motivazioneComboBox.getSelectedItem());
            prenotazioneListener.addPrenotazione(prenotazione);

            dispose();
        });
        ctaPanel.add(buttonAnnulla, BorderLayout.WEST);
        ctaPanel.add(buttonConferma, BorderLayout.EAST);

        return ctaPanel;
    }

    private JPanel createOrarioPanel(int indexStart) {
        JPanel orarioPanel = new JPanel();
        orarioPanel.setLayout(new BoxLayout(orarioPanel, BoxLayout.X_AXIS));
        oraInizioCombo = new JComboBox<>(Arrays.copyOfRange(Costanti.ORARI_AMMESSI, 0, Costanti.ORARI_AMMESSI.length - 1));
        oraFineCombo = new JComboBox<>(Costanti.ORARI_AMMESSI);

        oraInizioCombo.addActionListener(e -> aggiornaOrarioFineAmmesso());
        oraInizioCombo.setSelectedIndex(indexStart);

        orarioPanel.add(oraInizioCombo);
        orarioPanel.add(oraFineCombo);

        return orarioPanel;
    }

    private JSpinner createDateSpinner(LocalDate currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();

        dateSpinner = new JSpinner(new SpinnerDateModel(Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), startDate, endDate, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        return dateSpinner;
    }

    private JPanel createMotivazionePanel() {
        JPanel motivazionePanel = new JPanel();
        motivazionePanel.setLayout(new BoxLayout(motivazionePanel, BoxLayout.X_AXIS));
        motivazioneComboBox = new JComboBox<>(Costanti.MOTIVAZIONI);

        motivazionePanel.add(motivazioneComboBox);

        return motivazionePanel;
    }

    private JPanel createAulaPanel(Aula aula) {
        JPanel aulaPanel = new JPanel();
        aulaPanel.setLayout(new BoxLayout(aulaPanel, BoxLayout.X_AXIS));
        tipologiaComboBox = new JComboBox<>(new String[]{AULA_DIDATTICA, LABORATORIO});
        aulaComboBox = new JComboBox<>();

        tipologiaComboBox.addActionListener(e -> filterAule((String) tipologiaComboBox.getSelectedItem()));

        tipologiaComboBox.setSelectedIndex(aula.getTipologia().equals(AULA_DIDATTICA) ? 0 : 1);
        aulaComboBox.setSelectedIndex(aula.getNumeroAula());

        aulaPanel.add(tipologiaComboBox);
        aulaPanel.add(aulaComboBox);

        return aulaPanel;
    }

    private JPanel createNomePanel(){
        JPanel nomePanel = new JPanel();
        nomePanel.setLayout(new BoxLayout(nomePanel, BoxLayout.X_AXIS));
        nomeTextField = new JTextField(20);
        nomeTextField.setFont(new Font("Dialog", Font.BOLD, 12));
        nomeTextField.setMargin(new Insets(2,2,2,2));

        nomeTextField.getDocument().addDocumentListener((SimpleDocumentListener) e -> checkValidity());

        nomePanel.add(new JLabel("Nome*"));
        nomePanel.add(Box.createHorizontalStrut(10));
        nomePanel.add(nomeTextField);

        return nomePanel;
    }

    private void filterAule(String tipologia) {
        aulaComboBox.removeAllItems();
        for(int i = 0; i < Costanti.AULE.length; i++){
            if(Costanti.AULE[i].getTipologia().equals(tipologia)) aulaComboBox.addItem(Costanti.AULE[i]);
        }

        aggiornaOrarioFineAmmesso();
    }

    private void aggiornaOrarioFineAmmesso(){
//        int oraInizioSelezionato = oraInizioCombo.getSelectedIndex();
//        int step = tipologiaComboBox.getSelectedItem() == AULA_DIDATTICA ? 1 : 2;
//
//        oraFineCombo.removeAllItems();
//        for(int i = oraInizioSelezionato + step; i < Costanti.ORARI_AMMESSI.length; i += step){
//            oraFineCombo.addItem(Costanti.ORARI_AMMESSI[i]);
//        }
//
//        oraFineCombo.setSelectedIndex(0);
    }

    private void checkValidity(){
        String nome = nomeTextField.getText().trim();
        boolean isValid = !nome.isEmpty() && nome.matches("[a-zA-Z\\s]+");
        buttonConferma.setEnabled(isValid);
    }
}
