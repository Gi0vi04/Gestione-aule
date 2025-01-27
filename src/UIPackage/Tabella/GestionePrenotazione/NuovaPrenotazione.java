package UIPackage.Tabella.GestionePrenotazione;

import LogicaPackage.Aula;
import LogicaPackage.Prenotazione;
import LogicaPackage.Utils.InputOutput;
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
    private JComboBox<Aula> aulaComboBox;
    private JComboBox<String> motivazioneComboBox;
    private JSpinner dateSpinner;
    private JComboBox<LocalTime> oraInizioCombo;
    private JComboBox<LocalTime> oraFineCombo = new JComboBox<>();
    private JButton buttonConferma = new JButton("Conferma");

    public static final String DIDATTICA = "Didattica";
    private ArrayList<Aula> aule;

    public NuovaPrenotazione(int row, int column, PrenotazioneListener prenotazioneListener, LocalDate currentDate, ArrayList<Prenotazione> prenotazioni, ArrayList<Aula> aule){
        super("Nuova prenotazione");
        this.aule = aule;
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        mainPanel.add(createNomePanel());
        mainPanel.add(createAulaPanel(column - 1));
        mainPanel.add(createMotivazionePanel());
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createDateSpinner(currentDate));
        mainPanel.add(createOrarioPanel(row, prenotazioni));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createCTAPanel(prenotazioneListener, prenotazioni.size()));

        add(mainPanel);
        pack();
        super.setLocationRelativeTo(null);
    }

    private JPanel createCTAPanel(PrenotazioneListener prenotazioneListener, int codicePrenotazione) {
        JPanel ctaPanel = new JPanel(new BorderLayout());
        JButton buttonAnnulla = new JButton("Annulla");
        buttonAnnulla.addActionListener(e -> dispose());

        buttonConferma.setEnabled(false);
        buttonConferma.addActionListener(e -> {
            Date dateSelected = (Date) dateSpinner.getValue();
            LocalDate localDateSelected = dateSelected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Prenotazione prenotazione = new Prenotazione(
                    (Aula) aulaComboBox.getSelectedItem(),
                    localDateSelected,
                    (LocalTime) oraInizioCombo.getSelectedItem(),
                    (LocalTime) oraFineCombo.getSelectedItem(),
                    nomeTextField.getText(),
                    (String) motivazioneComboBox.getSelectedItem());
            prenotazioneListener.addPrenotazione(prenotazione);

            dispose();
        });
        ctaPanel.add(buttonAnnulla, BorderLayout.WEST);
        ctaPanel.add(buttonConferma, BorderLayout.EAST);

        return ctaPanel;
    }

    private JPanel createOrarioPanel(int startIndex, ArrayList<Prenotazione> prenotazioni) {
        JPanel orarioPanel = new JPanel();
        orarioPanel.setLayout(new BoxLayout(orarioPanel, BoxLayout.X_AXIS));

        calcolaOrariInizioDisponibili(prenotazioni);
        calcolaOrariFineDisponibili(prenotazioni);

        ArrayList<LocalTime> orariDisponibili = new ArrayList<>(Arrays.asList(
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(18, 0)
        ));

        oraInizioCombo.addActionListener(e -> calcolaOrariFineDisponibili(prenotazioni));
        oraInizioCombo.setSelectedItem(orariDisponibili.get(startIndex));

        orarioPanel.add(oraInizioCombo);
        orarioPanel.add(oraFineCombo);
        return orarioPanel;
    }

    private void calcolaOrariFineDisponibili(ArrayList<Prenotazione> prenotazioni) {
        Date selectedDate = (Date) dateSpinner.getValue();
        LocalDate selectedLocalDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Aula aulaSelected = (Aula) aulaComboBox.getSelectedItem();
        int hoursStep = aulaSelected.getTipologia().equals(DIDATTICA) ? 1 : 2;
        int maxHours = aulaSelected.getTipologia().equals(DIDATTICA) ? 8 : 4;
        LocalTime orarioInizio = (LocalTime) oraInizioCombo.getSelectedItem();

        LocalTime minOrarioInizio = LocalTime.of(18,0);
        for(int i = 0; i < prenotazioni.size(); i++){
            Prenotazione prenotazione = prenotazioni.get(i);

            if (prenotazione.getData().isEqual(selectedLocalDate) && prenotazione.getAula().getNumeroAula() == aulaSelected.getNumeroAula()) {
                LocalTime oraInizioPrenotazione = prenotazione.getOraInizio();
                if(oraInizioPrenotazione.isBefore(minOrarioInizio) && oraInizioPrenotazione.isAfter(orarioInizio)) minOrarioInizio = prenotazione.getOraInizio();
            }
        }


        oraFineCombo.removeAllItems();
        LocalTime currentTime = orarioInizio.plusHours(hoursStep);

        LocalTime maxHoursLocalTime = LocalTime.of(19,0);
        if(orarioInizio.plusHours(8).isBefore(LocalTime.of(18,0)) && orarioInizio.plusHours(8).isAfter(LocalTime.of(8,0))) maxHoursLocalTime = orarioInizio.plusHours(8);
        while(!currentTime.isAfter(minOrarioInizio) && currentTime.isBefore(maxHoursLocalTime)){
            oraFineCombo.addItem(currentTime);
            currentTime = currentTime.plusHours(hoursStep);
        }

        checkValidity();
    }

    private void calcolaOrariInizioDisponibili(ArrayList<Prenotazione> prenotazioni) {
        Date selectedDate = (Date) dateSpinner.getValue();
        LocalDate selectedLocalDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Aula aulaSelected = (Aula) aulaComboBox.getSelectedItem();

        ArrayList<LocalTime> orariOccupati = new ArrayList<>();
        for (int i = 0; i < prenotazioni.size(); i++) {
            Prenotazione prenotazione = prenotazioni.get(i);
            if (prenotazione.getData().isEqual(selectedLocalDate) && prenotazione.getAula().getNumeroAula() == aulaSelected.getNumeroAula()) {
                LocalTime orarioInizio = prenotazione.getOraInizio();
                LocalTime orarioFine = prenotazione.getOraFine();
                while (!orarioInizio.equals(orarioFine)) {
                    orariOccupati.add(orarioInizio);
                    orarioInizio = orarioInizio.plusHours(1);
                }
            }
        }

        ArrayList<LocalTime> orariDisponibili = new ArrayList<>(Arrays.asList(
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(18, 0)
        ));
        orariDisponibili.removeAll(orariOccupati);
        oraInizioCombo = new JComboBox<>(orariDisponibili.toArray(new LocalTime[0]));
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
        motivazioneComboBox = new JComboBox<>(InputOutput.MOTIVAZIONI);

        motivazionePanel.add(motivazioneComboBox);

        return motivazionePanel;
    }

    private JPanel createAulaPanel(int indexAula) {
        JPanel aulaPanel = new JPanel(new BorderLayout());
        aulaComboBox = new JComboBox<>(aule.toArray(new Aula[0]));
        aulaComboBox.setSelectedIndex(indexAula);

        aulaPanel.add(aulaComboBox, BorderLayout.CENTER);

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

    private void checkValidity(){
        String nome = nomeTextField.getText().trim();
        boolean isValid = !nome.isEmpty() && nome.matches("[a-zA-Z\\s]+") && oraFineCombo.getItemCount() > 0;
        buttonConferma.setEnabled(isValid);
    }
}
