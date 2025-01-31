package UIPackage.CustomUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Classe destinata a estendere il semplice JTextField per supportare il placeholder
 */
public class CustomTextField extends JTextField {
    /**
     * Variabile che contiene il testo del placeholder
     */
    private final String placeholder;
    /**
     * Variabile che contiene il colore (costante) del placeholder
     */
    private final Color placeholderColor = Color.GRAY;

    /**
     * Costruttore del CustomTextField con placeholder
     * @param placeholder il testo del placeholder
     */
    public CustomTextField(String placeholder) {
        this.placeholder = placeholder;
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(placeholderColor);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString(placeholder, getInsets().left + 5, getBaseline(getWidth(), getHeight()));
        }
    }
}
