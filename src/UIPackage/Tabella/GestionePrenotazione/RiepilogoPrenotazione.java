package UIPackage.Tabella.GestionePrenotazione;

import LogicaPackage.Utils.Costanti;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class RiepilogoPrenotazione extends JPanel {
    /**
     * Riferimento alla prenotazione attualmente impostata
     */
    private final GestisciPrenotazione prenotazione;
    /**
     * Riferimento al panel contenente il riepilogo
     */
    private final JPanel riepilogoPanel;
    /**
     * Label che mostra il nome
     */
    private final JLabel riepilogoNome;
    /**
     * Label che mostra l'aula
     */
    private final JLabel riepilogoAula;
    /**
     * Label che mostra i dettagli dell'aula
     */
    private final JLabel riepilogoDettagliAula;
    /**
     * Label che mostra la motivazione
     */
    private final JLabel riepilogoMotivazione;
    /**
     * Label che mostra la data
     */
    private final JLabel riepilogoData;
    /**
     * Label che mostra l'orario
     */
    private final JLabel riepilogoOrario;

    public RiepilogoPrenotazione(GestisciPrenotazione prenotazione){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.prenotazione = prenotazione;
        // Inizializzo lo stato
        this.riepilogoNome = new JLabel(prenotazione.getSelectedNome());
        this.riepilogoAula = new JLabel(prenotazione.getSelectedAula().getNomeAula());
        this.riepilogoDettagliAula = new JLabel(prenotazione.getSelectedAula().getDettagli());
        this.riepilogoMotivazione = new JLabel(prenotazione.getSelectedMotivazione());
        this.riepilogoData = new JLabel(String.valueOf(prenotazione.getSelectedDate()));
        this.riepilogoOrario = new JLabel(prenotazione.getSelectedOraInizio() + " - " + prenotazione.getSelectedOraFine());

        JPanel riepilogoLabelPanel = new JPanel(new BorderLayout());
        JLabel riepilogoLabel = new JLabel("Riepilogo prenotazione", SwingConstants.LEFT);
        riepilogoLabel.setFont(riepilogoLabel.getFont().deriveFont(Font.BOLD));
        riepilogoLabelPanel.add(riepilogoLabel, BorderLayout.WEST);

        riepilogoPanel = new JPanel();
        riepilogoPanel.setLayout(new BoxLayout(riepilogoPanel, BoxLayout.Y_AXIS));
        riepilogoPanel.setBorder(BorderFactory.createMatteBorder(1, 1,1,1, Color.DARK_GRAY));
        riepilogoPanel.setBackground(Costanti.COLORI_MOTIVAZIONI.get(prenotazione.getSelectedMotivazione()));

        riepilogoPanel.add(createRiepilogoNomePanel());
        riepilogoPanel.add(createRiepilogoAulaPanel());
        riepilogoPanel.add(createRiepilogoDettagliAulaPanel());
        riepilogoPanel.add(createRiepilogoMotivazione());
        riepilogoPanel.add(createRiepilogoData());
        riepilogoPanel.add(createRiepilogoOrario());

        add(riepilogoLabelPanel);
        add(Box.createVerticalStrut(3));
        add(riepilogoPanel);
    }

    /**
     * Aggiorna tutte le informazioni presenti nel riepilogo
     */
    public void aggiornaRiepilogo(){
        this.riepilogoNome.setText(!prenotazione.getSelectedNome().isEmpty() ? prenotazione.getSelectedNome() : "Inserire un nome");
        this.riepilogoAula.setText(prenotazione.getSelectedAula().getNomeAula());
        this.riepilogoDettagliAula.setText(prenotazione.getSelectedAula().getDettagli());
        this.riepilogoMotivazione.setText(prenotazione.getSelectedMotivazione());
        this.riepilogoData.setText(prenotazione.getSelectedDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.riepilogoOrario.setText(prenotazione.getSelectedOraInizio() + " - " + prenotazione.getSelectedOraFine());

        riepilogoPanel.setBackground(Costanti.COLORI_MOTIVAZIONI.get(prenotazione.getSelectedMotivazione()));
    }

    private JPanel createRiepilogoNomePanel() {
        JPanel riepilogoNomePanel = new JPanel(new BorderLayout());
        riepilogoNomePanel.setBorder(new EmptyBorder(2,5,2,5));
        riepilogoNomePanel.setOpaque(false);

        riepilogoNome.setFont(riepilogoNome.getFont().deriveFont(Font.ITALIC));
        riepilogoNomePanel.add(new JLabel("Nome:"), BorderLayout.WEST);
        riepilogoNomePanel.add(riepilogoNome, BorderLayout.EAST);
        return riepilogoNomePanel;
    }
    private JPanel createRiepilogoAulaPanel() {
        JPanel riepilogoAulaPanel = new JPanel(new BorderLayout());
        riepilogoAulaPanel.setBorder(new EmptyBorder(0,5,2,5));
        riepilogoAulaPanel.setOpaque(false);

        riepilogoAula.setFont(riepilogoAula.getFont().deriveFont(Font.ITALIC));
        riepilogoAulaPanel.add(new JLabel("Aula:"), BorderLayout.WEST);
        riepilogoAulaPanel.add(riepilogoAula, BorderLayout.EAST);
        return riepilogoAulaPanel;
    }
    private JPanel createRiepilogoDettagliAulaPanel() {
        JPanel riepilogoDettagliAulaPanel = new JPanel(new BorderLayout());
        riepilogoDettagliAulaPanel.setBorder(new EmptyBorder(0,5,2,5));
        riepilogoDettagliAulaPanel.setOpaque(false);

        riepilogoDettagliAula.setFont(riepilogoDettagliAula.getFont().deriveFont(Font.ITALIC));
        riepilogoDettagliAulaPanel.add(new JLabel("Dettagli aula:"), BorderLayout.WEST);
        riepilogoDettagliAulaPanel.add(riepilogoDettagliAula, BorderLayout.EAST);
        return riepilogoDettagliAulaPanel;
    }
    private JPanel createRiepilogoMotivazione() {
        JPanel riepilogoMotivazionePanel = new JPanel(new BorderLayout());
        riepilogoMotivazionePanel.setBorder(new EmptyBorder(0,5,2,5));
        riepilogoMotivazionePanel.setOpaque(false);

        riepilogoMotivazione.setFont(riepilogoMotivazione.getFont().deriveFont(Font.ITALIC));
        riepilogoMotivazionePanel.add(new JLabel("Motivazione:"), BorderLayout.WEST);
        riepilogoMotivazionePanel.add(riepilogoMotivazione, BorderLayout.EAST);
        return riepilogoMotivazionePanel;
    }
    private JPanel createRiepilogoData() {
        JPanel riepilogoDataPanel = new JPanel(new BorderLayout());
        riepilogoDataPanel.setBorder(new EmptyBorder(0,5,2,5));
        riepilogoDataPanel.setOpaque(false);

        riepilogoData.setFont(riepilogoData.getFont().deriveFont(Font.ITALIC));
        riepilogoDataPanel.add(new JLabel("Data:"), BorderLayout.WEST);
        riepilogoDataPanel.add(riepilogoData, BorderLayout.EAST);
        return riepilogoDataPanel;
    }
    private JPanel createRiepilogoOrario() {
        JPanel riepilogoOrarioPanel = new JPanel(new BorderLayout());
        riepilogoOrarioPanel.setBorder(new EmptyBorder(0,5,2,5));
        riepilogoOrarioPanel.setOpaque(false);

        riepilogoOrario.setFont(riepilogoOrario.getFont().deriveFont(Font.ITALIC));
        riepilogoOrarioPanel.add(new JLabel("Orario:"), BorderLayout.WEST);
        riepilogoOrarioPanel.add(riepilogoOrario, BorderLayout.EAST);
        return riepilogoOrarioPanel;
    }
}
