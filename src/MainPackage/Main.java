package MainPackage;

import LogicaPackage.AulaDidattica;
import UIPackage.SideBarPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Gestione aule");
        mainFrame.setSize(500, 200);
        mainFrame.setMinimumSize(new Dimension(500, 200));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //AulaDidattica didattica1 = new AulaDidattica(1,100,true,false);

        mainFrame.add(new SideBarPanel());
        mainFrame.setVisible(true);
    }
}