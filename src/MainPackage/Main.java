package MainPackage;

import LogicaPackage.Utils.Costanti;
import ThreadsPackage.AutoSaveThread;
import UIPackage.Intestazione;
import UIPackage.Tabella.TabellaPrenotazioni;

import javax.swing.*;
import java.awt.*;

/**
 * Classe destinata all'avvio del software
 */
public class Main {
    /**
     * Funzione di avviso del software
     * @param args argomenti passati da terminale (non utilizzati)
     */
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Gestione aule");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(900,800);
        mainFrame.setMinimumSize(new Dimension(800,720));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TabellaPrenotazioni tabellaPrenotazioni = new TabellaPrenotazioni();
        Intestazione intestazione = new Intestazione(tabellaPrenotazioni);

        AutoSaveThread autoSaveThread = new AutoSaveThread(tabellaPrenotazioni, Costanti.AUTOSAVE_PATH);
        autoSaveThread.start();

        mainFrame.add(intestazione, BorderLayout.NORTH);
        mainFrame.add(tabellaPrenotazioni, BorderLayout.CENTER);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}