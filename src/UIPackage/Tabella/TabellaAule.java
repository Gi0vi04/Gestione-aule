package UIPackage.Tabella;

import LogicaPackage.Prenotazione;
import UIPackage.Tabella.NuovaPrenotazione.NuovaPrenotazione;
import UIPackage.Tabella.NuovaPrenotazione.NuovaPrenotazioneListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class TabellaAule extends JPanel implements NuovaPrenotazioneListener {
    public JTable table;
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
        table.setAlignmentX(CENTER_ALIGNMENT);
        table.setFillsViewportHeight(true);
        // Configura il ridimensionamento delle colonne
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Aggiungo un listener per aprire un frame al clic su una cella
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();

                NuovaPrenotazione nuovaPrenotazione = new NuovaPrenotazione(row, column, TabellaAule.this);
                nuovaPrenotazione.setVisible(true);
            }
        });

        // Aggiungo un ComponentListener alla tabella per gestire il ridimensionamento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Calcola la nuova altezza delle righe
                int height = getHeight();
                int headerHeight = table.getTableHeader().getHeight();
                int rowCount = table.getRowCount();

                // Sottrai l'altezza dell'header e dividi per il numero di righe
                int newRowHeight = (height - headerHeight) / rowCount;

                // Imposta la nuova altezza (con un minimo di 50 pixel)
                table.setRowHeight(Math.max(50, newRowHeight));
            }
        });

        // Aggiungo la tabella in uno JScrollPane e lo posiziono al centro del panel
        JScrollPane scrollPane = new JScrollPane(table);

        //Carico le prenotazioni (dall'inizio) e aggiungo la tabella al panel
        caricaPrenotazioni(0);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void caricaPrenotazioni(int start){
        for(int i = start; i < prenotazioni.size(); i++){
            Prenotazione prenotazione = prenotazioni.get(i);

            //Filtro solo le prenotazioni della data selezionata
            if(prenotazione.getData().isEqual(LocalDate.now())){
                int oraInizio = prenotazioni.get(i).getOraInizio();
                int oraFine = prenotazioni.get(i).getOraFine();

                for(int j = oraInizio; j <= oraFine; j++){
                    table.setValueAt(prenotazioni.get(i).getNomePrenotante(), j, prenotazioni.get(i).getCodiceAula());
                }
            }
        }
    }

    @Override
    public void onPrenotazioneAggiunta(Prenotazione prenotazione) {
        int nextStart = prenotazioni.size();

        prenotazioni.add(prenotazione);
        caricaPrenotazioni(nextStart);
    }
}
