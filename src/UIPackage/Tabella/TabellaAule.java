package UIPackage.Tabella;

import LogicaPackage.Prenotazione;
import LogicaPackage.Utils.Dialog;
import UIPackage.Tabella.NuovaPrenotazione.NuovaPrenotazione;
import UIPackage.Tabella.NuovaPrenotazione.NuovaPrenotazioneListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class TabellaAule extends JPanel implements NuovaPrenotazioneListener {
    public JTable table;
    private LocalDate currentDate;
    private ArrayList<Prenotazione> prenotazioni;

    public TabellaAule(ArrayList<Prenotazione> prenotazioni) {
        setLayout(new BorderLayout());

        this.prenotazioni = prenotazioni;
        setupTable();
    }

    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public void setupTable(){
        table = new JTable(new TableModel());
        setCurrentDate(LocalDate.now());

        //Imposto l'altezza delle righe
        table.setRowHeight(25);
        table.setAlignmentX(CENTER_ALIGNMENT);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(true);
        // Configura il ridimensionamento delle colonne
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Aggiungo un listener per aprire un frame al clic su una cella
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();

                if(column > 0){
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

        // Aggiungo la tabella in uno JScrollPane e lo posiziono al centro del panel
        JScrollPane scrollPane = new JScrollPane(table);
        //Carico le prenotazioni (dall'inizio) e aggiungo la tabella al panel
        aggiornaPrenotazioni(0);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void changeCurrentDate(LocalDate newDate){
        setCurrentDate(newDate);
        clearTable();
        aggiornaPrenotazioni(0);
    }

    public void salvaPrenotazioni(){
        // Crea un JFileChooser
        JFileChooser fileChooser = new JFileChooser(){
            @Override
            public void approveSelection(){
                File file = getSelectedFile();
                if(file.exists() && getDialogType() == SAVE_DIALOG){
                    int result = JOptionPane.showConfirmDialog(this,"Il file esiste giá, vuoi sovrascriverlo?","File giá esistente",JOptionPane.YES_NO_CANCEL_OPTION);
                    switch(result){
                        case JOptionPane.YES_OPTION:
                            super.approveSelection();
                            return;
                        case JOptionPane.NO_OPTION:
                            return;
                        case JOptionPane.CLOSED_OPTION:
                            return;
                        case JOptionPane.CANCEL_OPTION:
                            cancelSelection();
                            return;
                    }
                }
                super.approveSelection();
            }
        };
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                new Dialog("Salvataggio avvenuto con successo");
            }
        } else {
            System.out.println("Operazione annullata dall'utente.");
        }
    }

    public void caricaPrenotazioni(){
        // Crea un JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Carica le prenotazioni");

        // Imposta un filtro per i file con estensione ".prenotazioni"
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File prenotazioni (*.prenotazioni)", "prenotazioni");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();

            if(!fileToLoad.getName().endsWith(".prenotazioni")){
                new Dialog("Il file non è del formato giusto");
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
                    } catch (EOFException e) {
                        break; //Fine del file
                    }
                }
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            } finally {
                setPrenotazioni(prenotazioniCaricate);
                clearTable();
                aggiornaPrenotazioni(0);
            }
        } else {
            System.out.println("Operazione annullata dall'utente.");
        }
    }

    private void aggiornaPrenotazioni(int start){
        for(int i = start; i < prenotazioni.size(); i++){
            Prenotazione prenotazione = prenotazioni.get(i);

            if(prenotazione.getData().isEqual(getCurrentDate())){
                int oraInizio = prenotazione.getOraInizio();
                int oraFine = prenotazione.getOraFine();

                for(int j = oraInizio; j < oraFine; j++){
                    table.setValueAt(prenotazione.getNomePrenotante(), j, prenotazione.getAula().getNumeroAula());
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
    public void onPrenotazioneAggiunta(Prenotazione prenotazione) {
        int nextStart = prenotazioni.size();

        prenotazioni.add(prenotazione);
        aggiornaPrenotazioni(nextStart);
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
}
