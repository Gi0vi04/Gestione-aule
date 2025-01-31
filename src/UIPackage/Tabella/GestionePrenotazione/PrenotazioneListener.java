package UIPackage.Tabella.GestionePrenotazione;

import LogicaPackage.Prenotazione;

/**
 * Interfaccia utilizzata per "collegare" il frame di gestione della prenotazione con la tabella delle prenotazioni
 */
public interface PrenotazioneListener {
    void addPrenotazione(Prenotazione prenotazione);
    void editPrenotazione(Prenotazione vecchiaPrenotazione, Prenotazione nuovaPrenotazione);
    void removePrenotazione(Prenotazione prenotazione);
}
