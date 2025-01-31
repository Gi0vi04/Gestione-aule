package ThreadsPackage;

import LogicaPackage.Prenotazione;
import UIPackage.Tabella.TabellaPrenotazioni;

import java.io.*;
import java.util.ArrayList;

/**
 * Class destinata a gestire il Thread del salvataggio automatico
 */
public class AutoSaveThread extends Thread {
    private final TabellaPrenotazioni tabellaPrenotazioni;
    private final String filePath;

    /**
     * Costruttore del thread di autosalvataggio
     * @param tabellaPrenotazioni tabella contenente le prenotazioni da salvare
     * @param filePath percorso del file di autosalvataggio
     */
    public AutoSaveThread(TabellaPrenotazioni tabellaPrenotazioni, String filePath) {
        this.tabellaPrenotazioni = tabellaPrenotazioni;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                savePrenotazioni(tabellaPrenotazioni.getPrenotazioni());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Salva le prenotazioni su un file costante
     * @param prenotazioni le prenotazioni da salvare
     */
    private void savePrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        ObjectOutputStream objectOutputStream;
        try {
            FileOutputStream autoSaveFile = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(autoSaveFile);

            for (Prenotazione prenotazione : prenotazioni) objectOutputStream.writeObject(prenotazione);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("[SALVATAGGIO AUTOMATICO]: Salvataggio automatico avvenuto con successo.");
        }
    }
}

