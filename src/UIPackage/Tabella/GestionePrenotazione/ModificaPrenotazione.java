package UIPackage.Tabella.GestionePrenotazione;

import LogicaPackage.Aula;
import LogicaPackage.Prenotazione;
import LogicaPackage.Utils.Costanti;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class ModificaPrenotazione extends JFrame {
    private JTextField nomeTextField;
    private JComboBox<Aula> aulaComboBox;
    private JComboBox<String> motivazioneComboBox;
    private JSpinner dateSpinner;
    private JComboBox<String> oraInizioCombo;
    private JComboBox<String> oraFineCombo;
    private JButton buttonConferma;

    public ModificaPrenotazione(Prenotazione prenotazione, PrenotazioneListener prenotazioneListener){
        super("Modifica prenotazione");
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        mainPanel.add(editNomePanel(prenotazione.getNome()));
        mainPanel.add(editAulaPanel(prenotazione.getAula()));
        mainPanel.add(editMotivazionePanel(prenotazione.getMotivazione()));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(editDateSpinner(prenotazione.getData()));
        mainPanel.add(editOrarioPanel(prenotazione.getOraInizio()));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(editCTAPanel(prenotazioneListener, prenotazione));

        add(mainPanel);
        pack();
        super.setLocationRelativeTo(null);
    }

    private JPanel editCTAPanel(PrenotazioneListener prenotazioneListener, Prenotazione prenotazioneIniziale) {
        JPanel ctaPanel = new JPanel(new BorderLayout());
        buttonConferma = new JButton("Conferma");
        JButton buttonElimina = new JButton("Elimina");
        buttonElimina.setForeground(Color.RED);
        buttonElimina.addActionListener(e -> {
            prenotazioneListener.removePrenotazione(prenotazioneIniziale);
            dispose();
        });

        buttonConferma.addActionListener(e -> {
            Date dateSelected = (Date) dateSpinner.getValue();
            LocalDate localDateSelected = dateSelected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime startTime = LocalTime.parse((String) oraInizioCombo.getSelectedItem());
            LocalTime endTime = LocalTime.parse((String) oraFineCombo.getSelectedItem());

            Prenotazione nuovaPrenotazione = new Prenotazione(
                    (Aula) aulaComboBox.getSelectedItem(),
                    localDateSelected,
                    startTime,
                    endTime,
                    nomeTextField.getText(),
                    (String) motivazioneComboBox.getSelectedItem());

            prenotazioneListener.editPrenotazione(nuovaPrenotazione);
            dispose();
        });
        ctaPanel.add(buttonElimina, BorderLayout.WEST);
        ctaPanel.add(buttonConferma, BorderLayout.EAST);

        return ctaPanel;
    }

    private JPanel editOrarioPanel(LocalTime oraInizio) {
        JPanel orarioPanel = new JPanel();
        orarioPanel.setLayout(new BoxLayout(orarioPanel, BoxLayout.X_AXIS));
        oraInizioCombo = new JComboBox<>(Arrays.copyOfRange(Costanti.ORARI_AMMESSI, 0, Costanti.ORARI_AMMESSI.length - 1));
        oraFineCombo = new JComboBox<>(Costanti.ORARI_AMMESSI);

//        oraInizioCombo.addActionListener(e -> aggiornaOrarioFineAmmesso());
//        oraInizioCombo.setSelectedIndex(oraInizio);

        orarioPanel.add(oraInizioCombo);
        orarioPanel.add(oraFineCombo);

        return orarioPanel;
    }

    private JSpinner editDateSpinner(LocalDate data) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();

        dateSpinner = new JSpinner(new SpinnerDateModel(Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()), startDate, endDate, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        return dateSpinner;
    }

    private JPanel editMotivazionePanel(String previousValue) {
        JPanel motivazionePanel = new JPanel();
        motivazionePanel.setLayout(new BoxLayout(motivazionePanel, BoxLayout.X_AXIS));
        motivazioneComboBox = new JComboBox<>(Costanti.MOTIVAZIONI);
        motivazioneComboBox.setSelectedItem(previousValue);

        motivazionePanel.add(motivazioneComboBox);

        return motivazionePanel;
    }

    private JPanel editAulaPanel(Aula previousValue) {
        JPanel aulaPanel = new JPanel(new BorderLayout());
        aulaComboBox = new JComboBox<>(Costanti.AULE);
        aulaComboBox.setSelectedItem(previousValue);

        aulaPanel.add(aulaComboBox, BorderLayout.CENTER);

        return aulaPanel;
    }

    private JPanel editNomePanel(String previousValue) {
        JPanel nomePanel = new JPanel();
        nomePanel.setLayout(new BoxLayout(nomePanel, BoxLayout.X_AXIS));
        nomeTextField = new JTextField(20);
        nomeTextField.setFont(new Font("Dialog", Font.BOLD, 12));
        nomeTextField.setMargin(new Insets(2,2,2,2));
        nomeTextField.setText(previousValue);

        nomePanel.add(new JLabel("Nome*"));
        nomePanel.add(Box.createHorizontalStrut(10));
        nomePanel.add(nomeTextField);

        return nomePanel;
    }
}
