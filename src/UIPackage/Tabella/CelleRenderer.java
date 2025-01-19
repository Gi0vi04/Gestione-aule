package UIPackage.Tabella;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CelleRenderer extends DefaultTableCellRenderer {
    private static final Font BOLD_FONT = new Font("SansSerif", Font.BOLD, 12);

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column
    ) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Colore di default
        cell.setBackground(Color.WHITE);
        cell.setForeground(Color.BLACK);

        // Logica per colorare celle interne
        if (column > 0 && value != null) {
            String text = value.toString();

            if (text.equalsIgnoreCase("occupato")) {
                cell.setBackground(Color.RED);
                cell.setForeground(Color.WHITE);
            } else if (text.equalsIgnoreCase("disponibile")) {
                cell.setBackground(Color.GREEN);
                cell.setForeground(Color.BLACK);
            }
        }

        if(column == 0){
            cell.setBackground(Color.LIGHT_GRAY);
            cell.setFont(BOLD_FONT);
        }

        return cell;
    }
}
