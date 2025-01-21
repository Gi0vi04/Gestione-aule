package LogicaPackage.Utils;

import javax.swing.*;

public class Dialog extends JDialog {
    public Dialog(String text){
        super();
        setTitle("Avviso");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        JButton closeButton = new JButton("Chiudi");
        closeButton.addActionListener(e -> dispose());

        setLayout(new java.awt.BorderLayout());
        add(label, java.awt.BorderLayout.CENTER);
        add(closeButton, java.awt.BorderLayout.SOUTH);

        setVisible(true);
    }
}
