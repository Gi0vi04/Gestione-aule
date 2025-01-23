package LogicaPackage.Utils;

import javax.swing.*;
import java.io.File;

public class CustomFileChooser extends JFileChooser {
    @Override
    public void approveSelection() {
        File file = getSelectedFile();
        if (file.exists() && getDialogType() == SAVE_DIALOG) {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Il file esiste già, vuoi sovrascriverlo?",
                    "File già esistente",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );

            switch (result) {
                case JOptionPane.YES_OPTION:
                    super.approveSelection(); // Conferma la selezione
                    return;
                case JOptionPane.NO_OPTION:
                case JOptionPane.CLOSED_OPTION:
                case JOptionPane.CANCEL_OPTION:
                    cancelSelection(); // Annulla la selezione
                    return;
            }
        }
        super.approveSelection(); // Conferma la selezione
    }
}
