package LogicaPackage.Utils;

import LogicaPackage.Aula;
import LogicaPackage.AulaDidattica;
import LogicaPackage.Laboratorio;

public final class Costanti {

    private Costanti() {
        // Costruttore privato per impedire l'instanziazione
    }

    public static final String[] ORARI_AMMESSI = {
            "08:00", "09:00", "10:00", "11:00", "12:00",
            "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",};

    public static final Aula[] AULE = {
            new AulaDidattica(0, "Aula didattica",100,true,false, "Didattica 1"),
            new AulaDidattica(1, "Aula didattica",70,false,false, "Didattica 2"),
            new AulaDidattica(2, "Aula didattica",85,true,false, "Didattica 3"),
            new AulaDidattica(3, "Aula didattica",100,true,true, "Didattica 4"),
            new Laboratorio(0, "Aula didattica",100,true,false, "Laboratorio 1"),
            new Laboratorio(1, "Aula didattica",120,true,true, "Laboratorio 2"),
    };

    public static final String[] MOTIVAZIONI = {
            "Lezione", "Esame", "Tutorato"
    };
}

