package UIPackage.CustomUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Classe destinata a mostrare un pop-up di dialogo.
 */
public class CustomDialog extends JDialog {
    /**
     * Costruttore del CustomDialog per lanciare un avviso custom
     * @param title titolo dell'avviso
     * @param subtitle sottotitolo dell'avviso
     * @param text testo dell'avviso
     */
    public CustomDialog(String title, String subtitle, String text) {
        super();
        setTitle(title);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(Font.BOLD));
        JLabel textLabel = new JLabel(text);

        dialogPanel.add(subtitleLabel);
        dialogPanel.add(Box.createVerticalStrut(5));
        dialogPanel.add(textLabel);
        dialogPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Chiudi");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);

        mainPanel.add(dialogPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}