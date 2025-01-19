package UIPackage.Tabella;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabellaAule extends JTable {
    public TabellaAule() {
        super(new ModelloTabella());// Imposta un renderer personalizzato per le celle
        //Personalizzo le celle della tabella
        this.setDefaultRenderer(Object.class, new CelleRenderer());
        //Imposto l'altezza delle righe
        this.setRowHeight(50);

        // Aggiungo un listener per aprire un frame al clic su una cella
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = getSelectedRow();
                int column = getSelectedColumn();

                // Evita di aprire il frame per l'intestazione o la colonna "Orario"
                if (row != -1 && column > 0) {
                    Object value = getValueAt(row, column);
                    String currentValue = value != null ? value.toString() : "";
                    new NuovaPrenotazioneFrame(row, column, currentValue).setVisible(true);
                }
            }
        });
    }
}
