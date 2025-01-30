package UIPackage.Tabella;

import LogicaPackage.Prenotazione;
import LogicaPackage.Utils.Costanti;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer extends JPanel implements TableCellRenderer {
    public CellRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Pulisco il contenuto e imposto il layout
        this.removeAll();
        this.setLayout(new BorderLayout());

        // Combino un bordo esterno e un margine interno
        Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY);
        Border margin = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border combinedBorder = BorderFactory.createCompoundBorder(border, margin);
        this.setBorder(combinedBorder);

        if (column == 0){
            this.setBackground(Color.LIGHT_GRAY);
            JLabel orario = new JLabel(value.toString(), SwingConstants.CENTER);
            this.add(orario, BorderLayout.CENTER);
        }
        else this.setBackground(Color.WHITE);

        // Imposto il colore per la cella selezionata
        if(isSelected){
            this.setBackground(Color.LIGHT_GRAY);
        }

        // Imposto il colore personalizzato per motivazione e creo la cella
        if(value instanceof Prenotazione prenotazione){
            this.setBackground(Costanti.COLORI_MOTIVAZIONI.get(prenotazione.getMotivazione()));
            this.setForeground(Color.BLACK);

            JPanel prenotazionePanel = new JPanel();
            prenotazionePanel.setBackground(this.getBackground());
            prenotazionePanel.setLayout(new BoxLayout(prenotazionePanel, BoxLayout.Y_AXIS));

            JLabel nomeLabel = new JLabel(prenotazione.getNome());
            JLabel motivazioneLabel = new JLabel(prenotazione.getMotivazione());
            JLabel orarioLabel = new JLabel(prenotazione.getOraInizio() + " - " + prenotazione.getOraFine());
            nomeLabel.setFont(nomeLabel.getFont().deriveFont(Font.BOLD));
            motivazioneLabel.setFont(nomeLabel.getFont().deriveFont(Font.ITALIC));
            orarioLabel.setFont(nomeLabel.getFont().deriveFont(Font.ITALIC));

            prenotazionePanel.add(nomeLabel);
            prenotazionePanel.add(motivazioneLabel);
            prenotazionePanel.add(orarioLabel);
            this.add(prenotazionePanel);
        }

        return this;
    }
}
