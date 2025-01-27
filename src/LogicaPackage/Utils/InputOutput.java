package LogicaPackage.Utils;

import LogicaPackage.Aula;
import LogicaPackage.AulaDidattica;
import LogicaPackage.Laboratorio;

import java.io.*;
import java.util.ArrayList;

public final class InputOutput {

    private InputOutput() {
        // Costruttore privato per impedire l'instanziazione
    }

    public static ArrayList<Aula> loadAule(){
        ArrayList<Aula> aule = new ArrayList<>();
        File input = new File("aule.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));

            while(reader.ready()){
                String line = reader.readLine();

                String[] values = line.split(",");
                String type = values[1];
                if(type.equals("Didattica")){
                    aule.add(new AulaDidattica(Integer.parseInt(values[2]), Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]), Boolean.parseBoolean(values[5]), values[0]));
                }
                else{
                    aule.add(new Laboratorio(Integer.parseInt(values[2]), Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]), Boolean.parseBoolean(values[5]), values[0]));
                }

            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Errore. File non trovato.");
        } catch (IOException e) {
            System.err.println("Errore durante l'acquisizione dell'input.");
        }

        return aule;
    }

    public static final String[] ORARI_AMMESSI = {
            "08:00", "09:00", "10:00", "11:00", "12:00",
            "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",};

    public static final Aula[] AULE = {
            new AulaDidattica(1, 100,true,false, "Didattica 1"),
            new AulaDidattica(2, 70,false,false, "Didattica 2"),
            new AulaDidattica(3, 85,true,false, "Didattica 3"),
            new AulaDidattica(4, 100,true,true, "Didattica 4"),
            new Laboratorio(5, 100,true,false, "Laboratorio 1"),
            new Laboratorio(6, 120,true,true, "Laboratorio 2"),
    };

    public static final String[] MOTIVAZIONI = {
            "Lezione", "Esame", "Tutorato"
    };
}

