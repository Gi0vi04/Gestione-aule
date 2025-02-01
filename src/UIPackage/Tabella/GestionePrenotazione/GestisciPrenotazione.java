package UIPackage.Tabella.GestionePrenotazione;

import LogicaPackage.Aula;
import LogicaPackage.Prenotazione;
import LogicaPackage.Utils.Costanti;
import LogicaPackage.Utils.Helpers;
import UIPackage.CustomUI.CustomTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Classe destinata a realizzare l'interfaccia per gestire (aggiungere, modificare ed eliminare) una prenotazione
 */
public class GestisciPrenotazione extends JFrame {
    /**
     * Variabile contenente il nome selezionato
     */
    private String selectedNome;
    /**
     * Variabile contenente l'aula selezionata
     */
    private Aula selectedAula;
    /**
     * Variabile contenente la motivazione selezionata
     */
    private String selectedMotivazione;
    /**
     * Variabile contenente la data selezionata
     */
    private LocalDate selectedDate;
    /**
     * Variabile contenente l'ora di inizio selezionata
     */
    private LocalTime selectedOraInizio;
    /**
     * Variabile contenente l'ora di fine selezionata
     */
    private LocalTime selectedOraFine;

    /**
     * Variabile contenente la lista delle prenotazioni
     */
    private final ArrayList<Prenotazione> prenotazioni;
    /**
     * Variabile contenente il vettore delle aule
     */
    private final Aula[] aule;
    /**
     * Variabile contenente l'interfaccia per le operazioni su una prenotazione
     */
    private final PrenotazioneListener prenotazioneListener;
    /**
     * Variabile contenente la prenotazione selezionata (se presente)
     */
    private final Prenotazione selectedPrenotazione;

    /**
     * Variabile contenente la ComboBox sull'orario di inizio
     */
    private JComboBox<LocalTime> oraInizioCombo;
    /**
     * Variabile contenente la ComboBox sull'orario di fine
     */
    private JComboBox<LocalTime> oraFineCombo;
    /**
     * Riferimento all'interfaccia di riepilogo prenotazione
     */
    private final RiepilogoPrenotazione riepilogoPrenotazione;
    /**
     * Variabile contenente il bottone di conferma
     */
    private final JButton buttonSubmit;

    /**
     * Costruttore dell'interfaccia grafica per la "Nuova prenotazione"
     * @param row riga selezionata
     * @param column colonna selezionata
     * @param selectedDate data selezionata (visualizzata dalla tabella)
     * @param prenotazioni lista prenotazioni presenti
     * @param aule lista delle aule presenti
     * @param prenotazioneListener interfaccia che gestisce le azioni possibili (creazione, modifica, cancellazione)
     */
    public GestisciPrenotazione(int row, int column, LocalDate selectedDate, ArrayList<Prenotazione> prenotazioni, Aula[] aule, PrenotazioneListener prenotazioneListener) {
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
        this.selectedPrenotazione = null;

        this.riepilogoPrenotazione = new RiepilogoPrenotazione(this);
        this.buttonSubmit = new JButton("Conferma");

        // Creo il mainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(createHeaderPanel("Crea una nuova prenotazione", "Compila i campi per creare una nuova prenotazione"), BorderLayout.NORTH);
        mainPanel.add(createFormPanel(), BorderLayout.CENTER);
        mainPanel.add(createNuovaPrenotazioneFooterPanel(), BorderLayout.SOUTH);
        add(mainPanel);

        pack();
        setMinimumSize(new Dimension(300, getHeight()));
        // Evita che il JTextField prenda il focus iniziale
        SwingUtilities.invokeLater(this::requestFocusInWindow);

        super.setLocationRelativeTo(null);
    }
    /**
     * Costruttore dell'interfaccia grafica per "Modifica (e cancella) prenotazione"
     * @param prenotazioni lista prenotazioni presenti
     * @param aule lista delle aule
     * @param prenotazioneListener interfaccia che gestisce le azioni possibili (creazione, modifica, cancellazione)
     * @param selectedPrenotazione prenotazione selezionata (cliccata nella tabella)
     */
    public GestisciPrenotazione(ArrayList<Prenotazione> prenotazioni, Aula[] aule, PrenotazioneListener prenotazioneListener, Prenotazione selectedPrenotazione) {
        super("Modifica prenotazione");
        setResizable(false);
        // Inizializzo lo stato del frame
        this.selectedNome = selectedPrenotazione.getNome();
        this.selectedAula = selectedPrenotazione.getAula();
        this.selectedMotivazione = selectedPrenotazione.getMotivazione();
        this.selectedDate = selectedPrenotazione.getData();
        this.selectedOraInizio = selectedPrenotazione.getOraInizio();
        this.selectedOraFine = selectedPrenotazione.getOraFine();

        this.prenotazioni = prenotazioni;
        this.aule = aule;
        this.prenotazioneListener = prenotazioneListener;
        this.selectedPrenotazione = selectedPrenotazione;

        this.riepilogoPrenotazione = new RiepilogoPrenotazione(this);
        this.buttonSubmit = new JButton("Modifica");

        // Creo il mainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(createHeaderPanel("Modifica la prenotazione", "Modifica i campi della prenotazione o eliminala"), BorderLayout.NORTH);
        mainPanel.add(createFormPanel(), BorderLayout.CENTER);
        mainPanel.add(createModificaPrenotazioneFooter(), BorderLayout.SOUTH);
        add(mainPanel);

        pack();
        setMinimumSize(new Dimension(300, getHeight()));
        // Evita che il JTextField prenda il focus iniziale
        SwingUtilities.invokeLater(this::requestFocusInWindow);

        super.setLocationRelativeTo(null);
    }

    public String getSelectedNome(){
        return selectedNome;
    }
    public Aula getSelectedAula(){
        return selectedAula;
    }
    public String getSelectedMotivazione() {
        return selectedMotivazione;
    }
    public LocalDate getSelectedDate() {
        return selectedDate;
    }
    public LocalTime getSelectedOraInizio() {
        return selectedOraInizio;
    }
    public LocalTime getSelectedOraFine() {
        return selectedOraFine;
    }

    /**
     * Setter del nome selezionato
     * @param selectedNome nome selezionato
     */
    public void setSelectedNome(String selectedNome){
        this.selectedNome = selectedNome;

        buttonSubmit.setEnabled(Helpers.isFormPrenotazioneValido(selectedNome, oraInizioCombo, oraFineCombo));
        riepilogoPrenotazione.aggiornaRiepilogo();
    }
    /**
     * Setter dell'aula selezionata
     * @param selectedAula aula selezionata
     */
    public void setSelectedAula(Aula selectedAula) {
        this.selectedAula = selectedAula;

        updateOraInizioCombo(Helpers.calcolaOrariInizioDisponibili(prenotazioni, selectedAula, selectedDate, selectedPrenotazione));
        updateOraFineCombo(Helpers.calcolaOrariFineDisponibili(prenotazioni, selectedAula, selectedDate, selectedOraInizio, selectedPrenotazione));
        riepilogoPrenotazione.aggiornaRiepilogo();
    }
    /**
     * Setter della motivazione selezionata
     * @param selectedMotivazione motivazione selezionata
     */
    public void setSelectedMotivazione(String selectedMotivazione){
        this.selectedMotivazione = selectedMotivazione;

        riepilogoPrenotazione.aggiornaRiepilogo();
    }
    /**
     * Setter della data selezionata
     * @param selectedDate data selezionata
     */
    public void setSelectedDate(LocalDate selectedDate){
        this.selectedDate = selectedDate;

        updateOraInizioCombo(Helpers.calcolaOrariInizioDisponibili(prenotazioni, selectedAula, selectedDate, selectedPrenotazione));
        updateOraFineCombo(Helpers.calcolaOrariFineDisponibili(prenotazioni, selectedAula, selectedDate, selectedOraInizio, selectedPrenotazione));
        riepilogoPrenotazione.aggiornaRiepilogo();
    }
    /**
     * Setter dell'ora di inizio selezionata
     * @param oraInizio ora di inizio selezionata
     */
    public void setSelectedOraInizio(LocalTime oraInizio) {
        this.selectedOraInizio = oraInizio;

        updateOraFineCombo(Helpers.calcolaOrariFineDisponibili(prenotazioni, selectedAula, selectedDate, selectedOraInizio, selectedPrenotazione));
        riepilogoPrenotazione.aggiornaRiepilogo();
    }
    /**
     * Setter dell'ora di fine selezionata
     * @param oraFine ora di fine selezionata
     */
    public void setSelectedOraFine(LocalTime oraFine) {
        this.selectedOraFine = oraFine;

        riepilogoPrenotazione.aggiornaRiepilogo();
    }

    /**
     * Funzione che aggiorna gli orari di inizio presenti nella ComboBox
     * @param orariInizioDisponibili orari di inizio disponibili (selezionabili)
     */
    public void updateOraInizioCombo(ArrayList<LocalTime> orariInizioDisponibili){
        DefaultComboBoxModel<LocalTime> model = new DefaultComboBoxModel<>();
        model.addAll(orariInizioDisponibili);

        oraInizioCombo.setModel(model);

        if(oraInizioCombo.getItemCount() > 0){
            oraInizioCombo.setEnabled(true);
            // Se possibile mantengo la selezione precedente
            if(selectedOraInizio != null && orariInizioDisponibili.contains(selectedOraInizio)) oraInizioCombo.setSelectedItem(selectedOraInizio);
            else oraInizioCombo.setSelectedIndex(0);
        }
        else{
            oraInizioCombo.setEnabled(false);
            oraInizioCombo.addItem(LocalTime.MIDNIGHT);
        }

        buttonSubmit.setEnabled(Helpers.isFormPrenotazioneValido(selectedNome, oraInizioCombo, oraFineCombo));
    }
    /**
     * Funzione che aggiorna gli orari di fine presenti nella ComboBox
     * @param orariFineDisponibili orari di fine disponibili (selezionabili)
     */
    public void updateOraFineCombo(ArrayList<LocalTime> orariFineDisponibili){
        DefaultComboBoxModel<LocalTime> model = new DefaultComboBoxModel<>();
        model.addAll(orariFineDisponibili);

        oraFineCombo.setModel(model);

        if(oraFineCombo.getItemCount() > 0 && oraInizioCombo.isEnabled()){
            oraFineCombo.setEnabled(true);
            // Se possibile mantengo la selezione precedente
            if(selectedOraFine != null && orariFineDisponibili.contains(selectedOraFine)) oraFineCombo.setSelectedItem(selectedOraFine);
            else oraFineCombo.setSelectedIndex(0);
        }
        else{
            oraFineCombo.setEnabled(false);
            oraFineCombo.removeAllItems();
            oraFineCombo.addItem(LocalTime.MIDNIGHT);
        }

        buttonSubmit.setEnabled(Helpers.isFormPrenotazioneValido(selectedNome, oraInizioCombo, oraFineCombo));
    }

    private JPanel createHeaderPanel(String titleText, String subtitleText) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(titleText);
        JLabel subtitle = new JLabel(subtitleText);
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
        return formPanel;
    }
    private JPanel createNuovaPrenotazioneFooterPanel(){
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        footerPanel.add(Box.createVerticalStrut(5));
        footerPanel.add(riepilogoPrenotazione);
        footerPanel.add(Box.createVerticalStrut(5));
        footerPanel.add(createNuovaPrenotazioneCTAPanel());
        return footerPanel;
    }
    private JPanel createModificaPrenotazioneFooter() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        footerPanel.add(Box.createVerticalStrut(5));
        footerPanel.add(riepilogoPrenotazione);
        footerPanel.add(Box.createVerticalStrut(5));
        footerPanel.add(createModificaPrenotazioneCTAPanel());
        return footerPanel;
    }

    private CustomTextField createNomeTextField() {
        CustomTextField nomeTextField = new CustomTextField("Inserire il nome");
        nomeTextField.setText(selectedNome);

        nomeTextField.getDocument().addDocumentListener((CustomDocumentListener) e -> setSelectedNome(nomeTextField.getText()));
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

        LocalTime[] orariInizioDisponibili = Helpers.calcolaOrariInizioDisponibili(prenotazioni, selectedAula, selectedDate, selectedPrenotazione).toArray(new LocalTime[0]);
        LocalTime[] orariFineDisponibili = Helpers.calcolaOrariFineDisponibili(prenotazioni, selectedAula, selectedDate, selectedOraInizio, selectedPrenotazione).toArray(new LocalTime[0]);

        oraInizioCombo = new JComboBox<>(orariInizioDisponibili);
        oraFineCombo = new JComboBox<>(orariFineDisponibili);
        oraInizioCombo.addActionListener(e -> setSelectedOraInizio((LocalTime) oraInizioCombo.getSelectedItem()));
        oraFineCombo.addActionListener(e -> setSelectedOraFine((LocalTime) oraFineCombo.getSelectedItem()));

        oraInizioCombo.setSelectedItem(selectedOraInizio);
        orarioPanel.add(oraInizioCombo);
        orarioPanel.add(oraFineCombo);
        return orarioPanel;
    }
    private JPanel createNuovaPrenotazioneCTAPanel() {
        JPanel ctaPanel = new JPanel(new BorderLayout());

        JButton buttonAnnulla = new JButton("Annulla");
        buttonAnnulla.addActionListener(e -> dispose());

        buttonSubmit.setEnabled(Helpers.isFormPrenotazioneValido(selectedNome, oraInizioCombo, oraFineCombo));
        buttonSubmit.addActionListener(e -> {
            Prenotazione prenotazione = new Prenotazione(selectedNome, selectedAula, selectedMotivazione, selectedDate, selectedOraInizio, selectedOraFine);
            prenotazioneListener.addPrenotazione(prenotazione);

            dispose();
        });

        ctaPanel.add(buttonAnnulla, BorderLayout.WEST);
        ctaPanel.add(buttonSubmit, BorderLayout.EAST);
        return ctaPanel;
    }
    private JPanel createModificaPrenotazioneCTAPanel() {
        JPanel ctaPanel = new JPanel(new BorderLayout());

        JButton buttonDelete = new JButton("Elimina");
        buttonDelete.setForeground(Color.RED);
        buttonDelete.addActionListener(e -> {
            prenotazioneListener.removePrenotazione(selectedPrenotazione);

            dispose();
        });

        buttonSubmit.setEnabled(Helpers.isFormPrenotazioneValido(selectedNome, oraInizioCombo, oraFineCombo));
        buttonSubmit.addActionListener(e -> {
            Prenotazione nuovaPrenotazione = new Prenotazione(selectedNome, selectedAula, selectedMotivazione, selectedDate, selectedOraInizio, selectedOraFine);
            prenotazioneListener.editPrenotazione(selectedPrenotazione, nuovaPrenotazione);

            dispose();
        });

        ctaPanel.add(buttonDelete, BorderLayout.WEST);
        ctaPanel.add(buttonSubmit, BorderLayout.EAST);
        return ctaPanel;
    }
}