package ThreadsPackage;

import LogicaPackage.Prenotazione;
import UIPackage.Tabella.TabellaAule;

import java.io.*;
import java.util.ArrayList;

public class AutoSaveThread extends Thread {
    private final TabellaAule tabellaAule;
    private final String filePath;

    public AutoSaveThread(TabellaAule tabellaAule, String filePath) {
        this.tabellaAule = tabellaAule;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                savePrenotazioni(tabellaAule.getPrenotazioni());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

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

