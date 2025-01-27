package MainPackage;

import LogicaPackage.Aula;
import LogicaPackage.Utils.FileIO;
import UIPackage.InformazioniLaterali.Intestazione;
import UIPackage.Tabella.TabellaAule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Gestione aule");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(800,720);
        mainFrame.setMinimumSize(new Dimension(700,610));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Aula> aule = FileIO.loadAule();
        TabellaAule tabellaAule = new TabellaAule(aule);
        Intestazione intestazione = new Intestazione(tabellaAule);


        mainFrame.add(intestazione, BorderLayout.NORTH);
        mainFrame.add(tabellaAule, BorderLayout.CENTER);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}