package UIPackage.Tabella.GestionePrenotazione;

import LogicaPackage.Prenotazione;

public interface PrenotazioneListener {
    void addPrenotazione(Prenotazione prenotazione);
    void editPrenotazione(Prenotazione vecchiaPrenotazione, Prenotazione nuovaPrenotazione);
    void removePrenotazione(Prenotazione prenotazione);
}
