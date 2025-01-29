package LogicaPackage.Utils;

import LogicaPackage.Aula;
import LogicaPackage.AulaDidattica;
import LogicaPackage.Laboratorio;
import LogicaPackage.Prenotazione;
import UIPackage.CustomUI.CustomFileChooser;
import UIPackage.CustomUI.CustomDialog;
import UIPackage.Tabella.TabellaAule;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.ArrayList;

public final class FileIO {

    // Costruttore privato per evitare l'instanziazione
    private FileIO() {
        throw new UnsupportedOperationException("Questa è una classe di utility e non può essere istanziata.");
    }

    /**
     * Legge le aule (costanti) dal file "aule.txt"
     * @return il vettore di aule
     */
    public static ArrayList<Aula> loadAule(){
        ArrayList<Aula> aule = new ArrayList<>();
        File input = new File("aule.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            while(reader.ready()){
                String line = reader.readLine();

                String[] values = line.split(",");
                if(values[2].equals(Costanti.DIDATTICA)){
                    aule.add(new AulaDidattica(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]), Boolean.parseBoolean(values[5])));
                }
                else{
                    aule.add(new Laboratorio(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]), Boolean.parseBoolean(values[5])));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Errore. File non trovato.");
        } catch (IOException e) {
            System.err.println("Errore durante l'acquisizione dell'input.");
        }

        return aule;
    }

    /**
     * Salva le prenotazioni su un file scelto dall'utente
     */
    public static void savePrenotazioni(ArrayList<Prenotazione> prenotazioni){
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
            catch (IOException e) { new CustomDialog("Salvataggio delle prenotazioni", "Errore durante il salvataggio delle prenotazioni", "Qualcosa non ha funzionato e le tue prenotazioni non sono state salvate."); }
            finally { new CustomDialog("Salvataggio delle prenotazioni", "Salvataggio avvenuto con successo", "Le tue prenotazioni sono state salvate correttamente."); }
        } else {
            System.out.println("Operazione annullata dall'utente.");
        }
    }

    /**
     * Carica le prenotazioni da un file scelto dall'utente
     */
    public static void loadPrenotazioni(TabellaAule tabellaAule){
        CustomFileChooser fileChooser = new CustomFileChooser();
        fileChooser.setDialogTitle("Carica le prenotazioni");

        // Imposta un filtro per i file con estensione ".prenotazioni"
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File prenotazioni (*.prenotazioni)", "prenotazioni");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();

            if(!fileToLoad.getName().endsWith(".prenotazioni")){
                new CustomDialog("Caricamento delle prenotazioni", "Errore durante il caricamento delle prenotazioni", "Il formato del file potrebbe non essere quello corretto.");
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
            catch (ClassNotFoundException | IOException e) { new CustomDialog("Caricamento delle prenotazioni", "Errore durante il caricamento delle prenotazioni", "Qualcosa non ha funzionato e le tue prenotazioni non sono state caricate."); }
            finally {
                tabellaAule.setPrenotazioni(prenotazioniCaricate);
                tabellaAule.refreshTable(true);
            }
        } else {
            System.out.println("Operazione annullata dall'utente.");
        }
    }

    /**
     * Permette di stampare le prenotazioni visualizzate nella tabella
     */
    public static void printPrenotazioni(JTable table){
        try {
            table.print();
        } catch (PrinterException e) {
            throw new RuntimeException(e);
        }
    }
}
