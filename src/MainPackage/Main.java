package MainPackage;

import LogicaPackage.Aula;
import UIPackage.InformazioniLaterali.Intestazione;
import UIPackage.Tabella.TabellaAule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static LogicaPackage.Utils.InputOutput.loadAule;

public class Main {
    public static void main(String[] args) {
        // Creazione del frame principale
        JFrame mainFrame = new JFrame("Gestione aule");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(800,720);
        mainFrame.setMinimumSize(new Dimension(700,610));

        // Creazione della tabella all'interno di uno JScrollPane
        ArrayList<Aula> aule = loadAule();
        TabellaAule tabellaAule = new TabellaAule(aule);
        //Creazione delle informazioni laterali
        Intestazione intestazione = new Intestazione(tabellaAule);

        //Inserisco gli elementi nel frame
        mainFrame.add(intestazione, BorderLayout.NORTH);
        mainFrame.add(tabellaAule, BorderLayout.CENTER);

        // Rendo visibile il frame
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}