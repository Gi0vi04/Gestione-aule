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

public class TabellaAule extends JPanel implements NuovaPrenotazioneListener {
    public JTable table;
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

        //Imposto l'altezza delle righe
        table.setRowHeight(25);
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

                if(column > 0){
                    NuovaPrenotazione nuovaPrenotazione = new NuovaPrenotazione(row, column, TabellaAule.this);
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
                table.setRowHeight(Math.max(25, newRowHeight));
            }
        });

        // Aggiungo la tabella in uno JScrollPane e lo posiziono al centro del panel
        JScrollPane scrollPane = new JScrollPane(table);
        //Carico le prenotazioni (dall'inizio) e aggiungo la tabella al panel
        aggiornaPrenotazioni(0);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void salvaPrenotazioni(){
        // Crea un JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salva le prenotazioni");

        // Imposta un filtro per i file con estensione ".prenotazioni"
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File prenotazioni (*.prenotazioni)", "prenotazioni");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(fileToSave.getAbsoluteFile() + ".prenotazioni");
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

        int userSelection = fileChooser.showSaveDialog(null);
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
                aggiornaPrenotazioni(0);
            }
        } else {
            System.out.println("Operazione annullata dall'utente.");
        }
    }

    private void aggiornaPrenotazioni(int start){
        clearTable();

        for(int i = start; i < prenotazioni.size(); i++){
            Prenotazione prenotazione = prenotazioni.get(i);

            int oraInizio = prenotazione.getOraInizio();
            int oraFine = prenotazione.getOraFine();

            for(int j = oraInizio; j < oraFine; j++){
                table.setValueAt(prenotazione.getNomePrenotante(), j, prenotazione.getAula().getNumeroAula());
            }
        }
    }

    private void clearTable() {
        AbstractTableModel model = (AbstractTableModel) table.getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                model.setValueAt(null, row, col);
            }
        }
    }

    @Override
    public void onPrenotazioneAggiunta(Prenotazione prenotazione) {
        int nextStart = prenotazioni.size();

        prenotazioni.add(prenotazione);
        aggiornaPrenotazioni(nextStart);
    }
}
