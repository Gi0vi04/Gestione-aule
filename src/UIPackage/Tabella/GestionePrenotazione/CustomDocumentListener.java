package UIPackage.Tabella.GestionePrenotazione;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Interfaccia destinata a rendere il codice più elegante e compatto quando il listener di un documento chiamerà la stessa funzione sui tre diversi eventi
 */
public interface CustomDocumentListener extends DocumentListener {
    /**
     * Funzione wrapper che semplifica e rende compatta la scrittura del DocumentListener
     * @param e corrisponde all'evento avvenuto
     */
    void update(DocumentEvent e);
    @Override default void insertUpdate(DocumentEvent e) { update(e); }
    @Override default void removeUpdate(DocumentEvent e) { update(e); }
    @Override default void changedUpdate(DocumentEvent e) { update(e); }
}
