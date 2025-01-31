package UIPackage.Tabella.GestionePrenotazione;

import LogicaPackage.Prenotazione;

/**
 * Interfaccia utilizzata per "collegare" il frame di gestione della prenotazione con la tabella delle prenotazioni
 */
public interface PrenotazioneListener {
    /**
     * Funzione per l'aggiunta di una prenotazione
     * @param prenotazione prenotazione da aggiungere
     */
    void addPrenotazione(Prenotazione prenotazione);
    /**
     * Funzione per la modifica di una prenotazione
     * @param vecchiaPrenotazione prenotazione da modificare
     * @param nuovaPrenotazione prenotazione modificata
     */
    void editPrenotazione(Prenotazione vecchiaPrenotazione, Prenotazione nuovaPrenotazione);
    /**
     * Funzione per la rimozione di una prenotazione
     * @param prenotazione prenotazione da rimuovere
     */
    void removePrenotazione(Prenotazione prenotazione);
}
