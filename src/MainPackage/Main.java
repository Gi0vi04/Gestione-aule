package MainPackage;

import UIPackage.SideBar.SideBarPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Gestione aule");
        //Imposto il frame full screen
        mainFrame.setSize(800,500);
        //Opzioni sul mainframe
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        //AulaDidattica didattica1 = new AulaDidattica(1,100,true,false);
        mainFrame.add(new SideBarPanel(), BorderLayout.WEST);
        mainFrame.setVisible(true);
    }
}