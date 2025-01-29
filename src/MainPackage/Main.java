package MainPackage;

import LogicaPackage.Aula;
import LogicaPackage.Utils.FileIO;
import UIPackage.CustomUI.CustomDialog;
import UIPackage.Intestazione;
import UIPackage.Tabella.TabellaAule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Gestione aule");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(900,800);
        mainFrame.setMinimumSize(new Dimension(800,720));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TabellaAule tabellaAule = new TabellaAule();
        Intestazione intestazione = new Intestazione(tabellaAule);

        mainFrame.add(intestazione, BorderLayout.NORTH);
        mainFrame.add(tabellaAule, BorderLayout.CENTER);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}