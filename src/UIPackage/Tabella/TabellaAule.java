package UIPackage.Tabella;

import LogicaPackage.Prenotazione;
import LogicaPackage.Utils.Costanti;
import LogicaPackage.Utils.CustomFileChooser;
import LogicaPackage.Utils.Dialog;
import UIPackage.Tabella.NuovaPrenotazione.NuovaPrenotazione;
import UIPackage.Tabella.NuovaPrenotazione.PrenotazioneListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TabellaAule extends JPanel implements PrenotazioneListener {
    public JTable table;
    private LocalDate currentDate;
    private ArrayList<Prenotazione> prenotazioni;

    public TabellaAule() {
        setLayout(new BorderLayout());
        this.prenotazioni = new ArrayList<>();
        this.currentDate = LocalDate.now();

        // Imposto la tabella
        table = new JTable(new TableModel());
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

                if(column > 0 && !currentDate.isBefore(LocalDate.now())){
                    NuovaPrenotazione nuovaPrenotazione = new NuovaPrenotazione(row, column, TabellaAule.this, currentDate);
                    nuovaPrenotazione.setVisible(true);
                }
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

    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;

        clearTable();
        refreshTable(0);
    }

    public LocalDate getCurrentDate(){
        return currentDate;
    }

    public void salvaPrenotazioni(){
        CustomFileChooser fileChooser = new CustomFileChooser();
        fileChooser.setDialogTitle("Salva le prenotazioni");

        // Imposta un filtro per i file con estensione ".prenotazioni"
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File prenotazioni (*.prenotazioni)", "prenotazioni");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            FileOutputStream fileOutputStream = null;
            try {
                if(!fileToSave.getName().endsWith(".prenotazioni")) fileOutputStream = new FileOutputStream(fileToSave.getAbsoluteFile() + ".prenotazioni");
                else fileOutputStream = new FileOutputStream(fileToSave.getAbsoluteFile());

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                for (Prenotazione prenotazione : prenotazioni) objectOutputStream.writeObject(prenotazione);
            }
            catch (IOException e) { new Dialog("Errore durante il salvataggio"); }
            finally { new Dialog("Salvataggio avvenuto con successo"); }
        } else {
            System.out.println("Operazione annullata dall'utente.");
        }
    }

    public void caricaPrenotazioni(){
        CustomFileChooser fileChooser = new CustomFileChooser();
        fileChooser.setDialogTitle("Carica le prenotazioni");

        // Imposta un filtro per i file con estensione ".prenotazioni"
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File prenotazioni (*.prenotazioni)", "prenotazioni");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();

            if(!fileToLoad.getName().endsWith(".prenotazioni")){
                new Dialog("Il file non è del formato giusto.");
                return;
            }

            ArrayList<Prenotazione> prenotazioniCaricate = new ArrayList<>();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(fileToLoad.getAbsoluteFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                while (true) {
                    try {
                        Prenotazione prenotazione = (Prenotazione) objectInputStream.readObject();
                        prenotazioniCaricate.add(prenotazione);
                    }
                    catch (EOFException e) { break; }
                }
            }
            catch (ClassNotFoundException | IOException e) { new Dialog("Errore durante il caricamento delle prenotazioni."); }
            finally {
                setPrenotazioni(prenotazioniCaricate);
                clearTable();
                refreshTable(0);
            }
        } else {
            System.out.println("Operazione annullata dall'utente.");
        }
    }

    private void refreshTable(int start){
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

    private void clearTable() {
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 1; col < table.getColumnCount(); col++) {
                table.setValueAt(null, row, col);
            }
        }
    }

    @Override
    public void addPrenotazione(Prenotazione prenotazione) {
        int nextStart = prenotazioni.size();

        prenotazioni.add(prenotazione);
        refreshTable(nextStart);
    }

    public void stampaPrenotazioni() {
        try {
            table.print();
        } catch (PrinterException e) {
            throw new RuntimeException(e);
        }
    }
}
