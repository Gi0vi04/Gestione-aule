package UIPackage.Tabella.GestionePrenotazione;

import LogicaPackage.Aula;
import LogicaPackage.Prenotazione;
import LogicaPackage.Utils.Costanti;
import LogicaPackage.Utils.Helpers;
import LogicaPackage.Utils.SimpleDocumentListener;
import UIPackage.CustomUI.CustomTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class NuovaPrenotazione extends JFrame {
    private String selectedNome;
    private Aula selectedAula;
    private String selectedMotivazione;
    private LocalDate selectedDate;
    private LocalTime selectedOraInizio;
    private LocalTime selectedOraFine;

    private final ArrayList<Prenotazione> prenotazioni;
    private final Aula[] aule;
    private final PrenotazioneListener prenotazioneListener;

    private JComboBox<LocalTime> oraInizioCombo;
    private JComboBox<LocalTime> oraFineCombo;
    private JButton buttonConferma;

    public NuovaPrenotazione(int row, int column, LocalDate selectedDate, ArrayList<Prenotazione> prenotazioni, Aula[] aule, PrenotazioneListener prenotazioneListener) {
        super("Nuova prenotazione");
        setResizable(false);
        // Inizializzo lo stato del frame
        this.selectedNome = "";
        this.selectedAula = aule[column - 1];
        this.selectedMotivazione = Costanti.MOTIVAZIONI[0];
        this.selectedDate = selectedDate;
        this.selectedOraInizio = Costanti.ORARI_AMMESSI.get(row);
        this.selectedOraFine = Costanti.ORARI_AMMESSI.get(row + 1);

        this.prenotazioni = prenotazioni;
        this.aule = aule;
        this.prenotazioneListener = prenotazioneListener;

        this.buttonConferma = new JButton("Conferma");
        // Creo il mainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createFormPanel(), BorderLayout.CENTER);
        add(mainPanel);

        pack();
        setMinimumSize(new Dimension(300, getHeight()));
        // Evita che il JTextField prenda il focus iniziale
        SwingUtilities.invokeLater(this::requestFocusInWindow);

        super.setLocationRelativeTo(null);
    }

    public void setSelectedNome(String selectedNome){
        this.selectedNome = selectedNome;

        buttonConferma.setEnabled(Helpers.isNomeValido(selectedNome));
    }
    public void setSelectedAula(Aula selectedAula) {
        this.selectedAula = selectedAula;

        updateOraInizioCombo(Helpers.calcolaOrariInizioDisponibili(prenotazioni, selectedAula, selectedDate));
        updateOraFineCombo(Helpers.calcolaOrariFineDisponibili(prenotazioni, selectedAula, selectedDate, selectedOraInizio));
    }
    public void setSelectedMotivazione(String selectedMotivazione){
        this.selectedMotivazione = selectedMotivazione;
    }
    public void setSelectedDate(LocalDate selectedDate){
        this.selectedDate = selectedDate;

        updateOraInizioCombo(Helpers.calcolaOrariInizioDisponibili(prenotazioni, selectedAula, selectedDate));
        updateOraFineCombo(Helpers.calcolaOrariFineDisponibili(prenotazioni, selectedAula, selectedDate, selectedOraInizio));
    }
    public void setSelectedOraInizio(LocalTime oraInizio) {
        this.selectedOraInizio = oraInizio;

        updateOraFineCombo(Helpers.calcolaOrariFineDisponibili(prenotazioni, selectedAula, selectedDate, selectedOraInizio));
    }
    public void setSelectedOraFine(LocalTime oraFine) {
        this.selectedOraFine = oraFine;
    }
    public void updateOraInizioCombo(ArrayList<LocalTime> orariInizioDisponibili){
        LocalTime selected = (LocalTime) oraInizioCombo.getSelectedItem();
        DefaultComboBoxModel<LocalTime> model = new DefaultComboBoxModel<>();
        model.addAll(orariInizioDisponibili);

        oraInizioCombo.setModel(model);

        if(selected != null && orariInizioDisponibili.contains(selected)) oraInizioCombo.setSelectedItem(selected);
        else oraInizioCombo.setSelectedIndex(0);
    }
    public void updateOraFineCombo(ArrayList<LocalTime> orariFineDisponibili){
        LocalTime selected = (LocalTime) oraInizioCombo.getSelectedItem();
        DefaultComboBoxModel<LocalTime> model = new DefaultComboBoxModel<>();
        model.addAll(orariFineDisponibili);

        oraFineCombo.setModel(model);
        if(selected != null && orariFineDisponibili.contains(selected)) oraFineCombo.setSelectedItem(selected);
        else if(oraFineCombo.getItemCount() > 0) oraFineCombo.setSelectedIndex(0);
        else oraFineCombo.setEnabled(false);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Crea una prenotazione");
        JLabel subtitle = new JLabel("Compila i campi per creare una nuova prenotazione");
        title.setFont(title.getFont().deriveFont(Font.BOLD));

        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(2));
        headerPanel.add(subtitle);
        headerPanel.add(Box.createVerticalStrut(6));
        return headerPanel;
    }
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        formPanel.add(createNomeTextField());
        formPanel.add(createAulaComboBox());
        formPanel.add(createMotivazioneComboBox());
        formPanel.add(createDateSpinner());
        formPanel.add(createOrarioPanel());
        formPanel.add(createCTAPanel());
        return formPanel;
    }

    private CustomTextField createNomeTextField() {
        CustomTextField nomeTextField = new CustomTextField("Inserire il nome");
        nomeTextField.setText(selectedNome);

        nomeTextField.getDocument().addDocumentListener((SimpleDocumentListener) e -> setSelectedNome(nomeTextField.getText()));
        return nomeTextField;
    }
    private JComboBox<Aula> createAulaComboBox() {
        JComboBox<Aula> aulaComboBox = new JComboBox<>(aule);
        aulaComboBox.setSelectedItem(selectedAula);

        aulaComboBox.addActionListener(e -> setSelectedAula((Aula) aulaComboBox.getSelectedItem()));
        return aulaComboBox;
    }
    private JComboBox<String> createMotivazioneComboBox() {
        JComboBox<String> motivazioneComboBox = new JComboBox<>(Costanti.MOTIVAZIONI);
        motivazioneComboBox.setSelectedItem(selectedMotivazione);

        motivazioneComboBox.addActionListener(e -> setSelectedMotivazione((String) motivazioneComboBox.getSelectedItem()));
        return motivazioneComboBox;
    }
    private JSpinner createDateSpinner() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date minDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        Date maxDate = calendar.getTime();

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), minDate, maxDate, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        dateSpinner.addChangeListener(e -> {
            Date selectedDate = (Date) dateSpinner.getValue();
            setSelectedDate(selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        });
        return dateSpinner;
    }
    private JPanel createOrarioPanel() {
        JPanel orarioPanel = new JPanel();
        orarioPanel.setLayout(new BoxLayout(orarioPanel, BoxLayout.X_AXIS));

        LocalTime[] orariInizioDisponibili = Helpers.calcolaOrariInizioDisponibili(prenotazioni, selectedAula, selectedDate).toArray(new LocalTime[0]);
        LocalTime[] orariFineDisponibili = Helpers.calcolaOrariFineDisponibili(prenotazioni, selectedAula, selectedDate, selectedOraInizio).toArray(new LocalTime[0]);

        oraInizioCombo = new JComboBox<>(orariInizioDisponibili);
        oraFineCombo = new JComboBox<>(orariFineDisponibili);
        oraInizioCombo.addActionListener(e -> setSelectedOraInizio((LocalTime) oraInizioCombo.getSelectedItem()));
        oraFineCombo.addActionListener(e -> setSelectedOraFine((LocalTime) oraFineCombo.getSelectedItem()));

        oraInizioCombo.setSelectedItem(selectedOraInizio);
        orarioPanel.add(oraInizioCombo);
        orarioPanel.add(oraFineCombo);
        return orarioPanel;
    }
    private JPanel createCTAPanel() {
        JPanel ctaPanel = new JPanel(new BorderLayout());

        JButton buttonAnnulla = new JButton("Annulla");
        buttonAnnulla.addActionListener(e -> dispose());

        buttonConferma.setEnabled(false);
        buttonConferma.addActionListener(e -> {
            Prenotazione prenotazione = new Prenotazione(selectedNome, selectedAula, selectedMotivazione, selectedDate, selectedOraInizio, selectedOraFine);
            prenotazioneListener.addPrenotazione(prenotazione);

            dispose();
        });

        ctaPanel.add(buttonAnnulla, BorderLayout.WEST);
        ctaPanel.add(buttonConferma, BorderLayout.EAST);
        return ctaPanel;
    }
}