package LogicaPackage.Utils;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Classe destinata a mantenere valori costanti nel tempo
 */
public final class Costanti {

    // Costruttore privato per evitare istanziazioni della classe
    private Costanti() {
        throw new UnsupportedOperationException("Questa è una classe di utility e non può essere istanziata");
    }

    // Definizione delle costanti
    /**
     * Stringa costante della parola "Didattica"
     */
    public static final String DIDATTICA = "Didattica";
    /**
     * Stringa costante della parola "Laboratorio"
     */
    public static final String LABORATORIO = "Laboratorio";
    /**
     * Lista contenente gli orari (costanti) ammessi per una prenotazione
     */
    public static final ArrayList<LocalTime> ORARI_AMMESSI = new ArrayList<>(Arrays.asList(
            LocalTime.of(8, 0),
            LocalTime.of(9, 0),
            LocalTime.of(10, 0),
            LocalTime.of(11, 0),
            LocalTime.of(12, 0),
            LocalTime.of(13, 0),
            LocalTime.of(14, 0),
            LocalTime.of(15, 0),
            LocalTime.of(16, 0),
            LocalTime.of(17, 0),
            LocalTime.of(18, 0)
    ));
    /**
     * Vettore contenente le possibili motivazioni di prenotazione
     */
    public static final String[] MOTIVAZIONI = {
            "Lezione", "Esame", "Tutorato", "Ricevimento", "Lauree", "Conferenza"
    };
    /**
     * Mappa MOTIVAZIONE-COLORE per ottenere un rapido ed elegante matching
     */
    public static final Map<String, Color> COLORI_MOTIVAZIONI = Map.of(
            "Lezione", new Color(0xF6F740),
            "Esame", new Color(0xFE938C),
            "Tutorato", new Color(0x08BDBD),
            "Ricevimento", new Color(0x9CADCE),
            "Lauree", new Color(0xF55929),
            "Conferenza", new Color(0x0FB37F)
    );
    /**
     * Costante contenente il percorso (e il nome) del file di salvataggio automatico
     */
    public static final String AUTOSAVE_PATH = "autosave.prenotazioni";
}

