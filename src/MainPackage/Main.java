package MainPackage;

import UIPackage.Tabella.TabellaAule;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Creazione del frame principale
        JFrame mainFrame = new JFrame("Gestione aule");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout());

        // Creazione della tabella all'interno di uno JScrollPane
        TabellaAule tabellaAule = new TabellaAule();
        JScrollPane scrollPane = new JScrollPane(tabellaAule);

        mainFrame.add(scrollPane);

        // Rendo visibile il frame
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}