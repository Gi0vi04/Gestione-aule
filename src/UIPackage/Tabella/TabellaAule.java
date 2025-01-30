package UIPackage.Tabella;

import LogicaPackage.Aula;
import LogicaPackage.Prenotazione;
//import UIPackage.Tabella.GestionePrenotazione.ModificaPrenotazione;
import LogicaPackage.Utils.FileIO;
import UIPackage.Tabella.GestionePrenotazione.GestisciPrenotazione;
import UIPackage.Tabella.GestionePrenotazione.PrenotazioneListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TabellaAule extends JPanel implements PrenotazioneListener {
    private final JTable table;
    private final ArrayList<Aula> aule;
    private ArrayList<Prenotazione> prenotazioni;
    private LocalDate selectedDate;

    public TabellaAule() {
        setLayout(new BorderLayout());
        aule = FileIO.loadAule();
        prenotazioni = new ArrayList<>();
        selectedDate = LocalDate.now();

        table = new JTable(new TableModel(aule));
        CellRenderer cellRenderer = new CellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        // Imposto la dimensione della colonna 0
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        // Imposto la selezione sulle singole celle
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);

        // Aggiungo un listener per aprire un frame al clic su una cella
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();

                if(column == 0 || selectedDate.isBefore(LocalDate.now())) return;

                Prenotazione prenotazione = (Prenotazione) table.getValueAt(row, column);
                if(prenotazione == null){
                    GestisciPrenotazione nuovaPrenotazione = new GestisciPrenotazione(row, column, selectedDate, prenotazioni, aule.toArray(new Aula[0]), TabellaAule.this);
                    nuovaPrenotazione.setVisible(true);
                }
                else{
                    GestisciPrenotazione modificaPrenotazione = new GestisciPrenotazione(prenotazioni, aule.toArray(new Aula[0]), TabellaAule.this, prenotazione);
                    modificaPrenotazione.setVisible(true);
                }
            }
        });

        // Aggiungo un ComponentListener alla tabella per gestire il ridimensionamento
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Calcola la nuova altezza delle righe
                int height = getHeight();
                int headerHeight = table.getTableHeader().getHeight();
                int rowCount = table.getRowCount();

                int newRowHeight = (height - headerHeight) / rowCount;
                table.setRowHeight(Math.max(30, newRowHeight));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getTable(){
        return table;
    }
    public ArrayList<Prenotazione> getPrenotazioni(){
        return prenotazioni;
    }
    public LocalDate getSelectedDate(){
        return selectedDate;
    }

    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
        refreshTable(true);
    }

    /**
     * Aggiorna la tabella inserendo le prenotazioni presenti
     */
    public void refreshTable(boolean clearTable){
        LocalTime referenceTime = LocalTime.parse("08:00");

        if(clearTable) clearTable();

        for (Prenotazione prenotazione : prenotazioni) {
            if (prenotazione.getData().isEqual(getSelectedDate())) {
                Aula aula = prenotazione.getAula();
                // Calcolo la riga di inizio e di fine
                LocalTime startTime = prenotazione.getOraInizio();
                LocalTime endTime = prenotazione.getOraFine();
                int startRow = startTime.getHour() - referenceTime.getHour();
                int endRow = startRow + (endTime.getHour() - startTime.getHour());

                for (int row = startRow; row < endRow; row++) {
                    table.setValueAt(prenotazione, row, aula.getNumeroAula());
                }
            }
        }
    }

    /**
     * Svuota la tabella rimuovendo tutte le prenotazioni presenti
     */
    private void clearTable() {
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 1; col < table.getColumnCount(); col++) {
                table.setValueAt(null, row, col);
            }
        }
    }

    /**
     * Aggiunge la prenotazione al vettore delle prenotazioni e aggiorna la tabella
     * @param prenotazione la prenotazione da aggiungere
     */
    @Override
    public void addPrenotazione(Prenotazione prenotazione) {
        prenotazioni.add(prenotazione);
        refreshTable(false);
    }

    /**
     * Modifica la vecchia prenotazione sostituendola con quella nuova
     * @param vecchiaPrenotazione prenotazione da sostituire
     * @param nuovaPrenotazione prenotazione modificata
     */
    @Override
    public void editPrenotazione(Prenotazione vecchiaPrenotazione, Prenotazione nuovaPrenotazione) {
        int indexVecchiaPrenotazione = prenotazioni.indexOf(vecchiaPrenotazione);
        prenotazioni.set(indexVecchiaPrenotazione, nuovaPrenotazione);
        refreshTable(true);
    }

    @Override
    public void removePrenotazione(Prenotazione prenotazione) {
        prenotazioni.remove(prenotazione);
        refreshTable(true);
    }
}
