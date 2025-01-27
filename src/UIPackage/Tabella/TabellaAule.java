package UIPackage.Tabella;

import LogicaPackage.Aula;
import LogicaPackage.Prenotazione;
//import UIPackage.Tabella.GestionePrenotazione.ModificaPrenotazione;
import UIPackage.Tabella.GestionePrenotazione.NuovaPrenotazione;
import UIPackage.Tabella.GestionePrenotazione.PrenotazioneListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TabellaAule extends JPanel implements PrenotazioneListener {
    public JTable table;
    private LocalDate currentDate;
    private ArrayList<Prenotazione> prenotazioni;

    public TabellaAule(ArrayList<Aula> aule) {
        setLayout(new BorderLayout());
        this.prenotazioni = new ArrayList<>();
        this.currentDate = LocalDate.now();

        // Imposto la tabella
        table = new JTable(new TableModel(aule));
        // Applicazione del renderer personalizzato a tutte le colonne
        table.setShowGrid(false);
        CellRenderer cellRenderer = new CellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        // Imposto la dimensione della colonna 0
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        // Imposto l'altezza delle righe
        table.setRowHeight(25);
        table.setAlignmentX(CENTER_ALIGNMENT);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        // Configura il ridimensionamento delle colonne
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // Aggiungo un listener per aprire un frame al clic su una cella
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();

                if(column == 0 || currentDate.isBefore(LocalDate.now())) return;

                Prenotazione prenotazione = (Prenotazione) table.getValueAt(row, column);
                if(prenotazione == null){
                    NuovaPrenotazione nuovaPrenotazione = new NuovaPrenotazione(row, column, TabellaAule.this, currentDate, prenotazioni, aule);
                    nuovaPrenotazione.setVisible(true);
                }
//                else{
//                    ModificaPrenotazione modificaPrenotazione = new ModificaPrenotazione(prenotazione, TabellaAule.this, aule);
//                    modificaPrenotazione.setVisible(true);
//                }
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
                table.setRowHeight(Math.max(30, newRowHeight));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public ArrayList<Prenotazione> getPrenotazioni(){
        return prenotazioni;
    }
    public LocalDate getCurrentDate(){
        return currentDate;
    }

    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;

        clearTable();
        refreshTable(0);
    }

    public void refreshTable(int start){
        LocalTime referenceTime = LocalTime.parse("08:00");

        for(int i = start; i < prenotazioni.size(); i++){
            Prenotazione prenotazione = prenotazioni.get(i);

            if(prenotazione.getData().isEqual(getCurrentDate())){
                // Calcolo la riga di inizio e di fine
                LocalTime startTime = prenotazione.getOraInizio();
                LocalTime endTime = prenotazione.getOraFine();

                int difference = endTime.getHour() - startTime.getHour();
                int startRow = startTime.getHour() - referenceTime.getHour();

                for(int j = startRow; j < startRow + difference; j++){
                    table.setValueAt(prenotazione, j, prenotazione.getAula().getNumeroAula());
                }
            }
        }
    }

    public void clearTable() {
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 1; col < table.getColumnCount(); col++) {
                table.setValueAt(null, row, col);
            }
        }
    }

    public void stampaPrenotazioni() {
        try {
            table.print();
        } catch (PrinterException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPrenotazione(Prenotazione prenotazione) {
        int nextStart = prenotazioni.size();

        prenotazioni.add(prenotazione);
        refreshTable(nextStart);
    }

    @Override
    public void editPrenotazione(Prenotazione vecchiaPrenotazione, Prenotazione nuovaPrenotazione) {
        prenotazioni.set(prenotazioni.indexOf(vecchiaPrenotazione), nuovaPrenotazione);
        clearTable();
        refreshTable(0);
    }

    @Override
    public void removePrenotazione(Prenotazione prenotazione) {
        prenotazioni.remove(prenotazione);
        clearTable();
        refreshTable(0);
    }
}
