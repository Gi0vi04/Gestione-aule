package UIPackage.Tabella;

import LogicaPackage.Prenotazione;
import UIPackage.NuovaPrenotazione.NuovaPrenotazione;
import UIPackage.NuovaPrenotazione.NuovaPrenotazioneListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TabellaAule extends JPanel implements NuovaPrenotazioneListener {
    private JTable table;
    private ArrayList<Prenotazione> prenotazioni;

    public TabellaAule(ArrayList<Prenotazione> prenotazioni) {
        setLayout(new BorderLayout());

        this.prenotazioni = prenotazioni;
        setupTable();
    }

    public void setupTable(){
        table = new JTable(new TableModel());
        //Imposto l'altezza delle righe
        table.setRowHeight(50);
        table.setAlignmentY(CENTER_ALIGNMENT);
        table.setFillsViewportHeight(true);
        // Configura il ridimensionamento delle colonne
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Aggiungo un listener per aprire un frame al clic su una cella
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();

                // Evita di aprire il frame per l'intestazione o la colonna "Orario"
                if (row != -1 && column > 0) {
                    NuovaPrenotazione nuovaPrenotazione = new NuovaPrenotazione(row, column, TabellaAule.this);
                    nuovaPrenotazione.setVisible(true);
                }
            }
        });

        // Aggiungi la tabella in uno JScrollPane e posizionalo al centro del panel
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        caricaPrenotazioni();

        add(scrollPane, BorderLayout.CENTER);
    }

    private void caricaPrenotazioni(){
        for(int i = 0; i < prenotazioni.size(); i++){
            int oraInizio = prenotazioni.get(i).getOraInizio();
            int oraFine = prenotazioni.get(i).getOraFine();

            for(int j = oraInizio; j <= oraFine; j++){
                table.setValueAt(prenotazioni.get(i).getNomePrenotante(), j, prenotazioni.get(i).getCodiceAula());
            }
        }
    }

    @Override
    public void onPrenotazioneAggiunta(Prenotazione prenotazione) {
        prenotazioni.add(prenotazione);
        caricaPrenotazioni();
    }
}
