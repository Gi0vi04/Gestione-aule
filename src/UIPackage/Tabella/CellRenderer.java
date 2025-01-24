package UIPackage.Tabella;

import LogicaPackage.Prenotazione;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CellRenderer extends DefaultTableCellRenderer {
    public CellRenderer() {
        // Centra il contenuto
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Imposto i bordi
        Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY); // Bordo spesso a destra
        setBorder(border);

        // Imposto uno sfondo sulla colonna 0
        if (column == 0) cell.setBackground(Color.LIGHT_GRAY);
        else cell.setBackground(Color.WHITE);

        // Imposto il colore per la cella selezionata
        if(isSelected){
            cell.setBackground(Color.LIGHT_GRAY);
        }

        // Imposto il colore personalizzato per motivazione
        if(value instanceof Prenotazione){
            switch (((Prenotazione) value).getMotivazionePrenotazione()){
                case "Lezione":
                    cell.setBackground(Color.ORANGE);
                    break;
                case "Esame":
                    cell.setBackground(Color.PINK);
                    break;
                case "Tutorato":
                    cell.setBackground(Color.CYAN);
                    break;
            }
            cell.setForeground(Color.BLACK);
        }

        return cell;
    }
}
