package UIPackage.InformazioniLaterali;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class OrarioLaterale extends JPanel {
    private GridLayout gridLayout;

    public OrarioLaterale() {
        // Inizializza il layout con un GridLayout
        gridLayout = new GridLayout(11, 1);
        setLayout(gridLayout);

        // Aggiungi le etichette degli orari
        for (int i = 0; i < 11; i++) {
            JPanel cellaOrario = new JPanel(new BorderLayout());


            JLabel label = new JLabel((i + 8) + ":00", SwingConstants.CENTER);
            cellaOrario.add(label, BorderLayout.CENTER);
            add(cellaOrario);
        }

        // Imposta il bordo per l'estetica
        setBorder(new EmptyBorder(15, 5, 5, 5));

        // Aggiungi un listener per gestire il ridimensionamento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Calcola l'altezza disponibile per le celle
                int altezzaTotale = getHeight();
                int altezzaCella = altezzaTotale / 11;

                // Aggiorna le dimensioni delle celle
                gridLayout.setVgap(Math.max(0, (altezzaTotale - 15 - (altezzaCella * 11)) / 2));
                revalidate();
                repaint();
            }
        });
    }
}
