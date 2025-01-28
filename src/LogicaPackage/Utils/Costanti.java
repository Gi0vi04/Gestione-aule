package LogicaPackage.Utils;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public final class Costanti {

    // Costruttore privato per evitare istanziazioni della classe
    private Costanti() {
        throw new UnsupportedOperationException("Questa è una classe di utility e non può essere istanziata");
    }

    // Definizione delle costanti
    public static final String DIDATTICA = "Didattica";
    public static final String LABORATORIO = "Laboratorio";
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
    public static final String[] MOTIVAZIONI = {
            "Lezione", "Esame", "Tutorato", "Ricevimento"
    };
    public static final Map<String, Color> COLORI_MOTIVAZIONI = Map.of(
            "Lezione", Color.ORANGE,
            "Esame", Color.PINK,
            "Tutorato", Color.CYAN,
            "Ricevimento", Color.GREEN
    );
}

