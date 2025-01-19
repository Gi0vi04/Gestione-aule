package MainPackage;

import LogicaPackage.Prenotazione;
import UIPackage.Tabella.TabellaAule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Prenotazione prenotazione1 = new Prenotazione(1, new Date(), 5,8,"Giovanni Distefano","Lezione");
        Prenotazione prenotazione2 = new Prenotazione(2, new Date(), 2,4,"Lorenzo Barberio","Lezione");
        Prenotazione prenotazione3 = new Prenotazione(3, new Date(), 4,6,"Giuseppe Mastrorillo","Lezione");
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        prenotazioni.add(prenotazione1);
        prenotazioni.add(prenotazione2);
        prenotazioni.add(prenotazione3);

        // Creazione del frame principale
        JFrame mainFrame = new JFrame("Gestione aule");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new GridLayout());
        mainFrame.setSize(700,610);

        // Creazione della tabella all'interno di uno JScrollPane
        TabellaAule tabellaAule = new TabellaAule(prenotazioni);

        mainFrame.add(tabellaAule);

        // Rendo visibile il frame
        mainFrame.setVisible(true);
    }
}