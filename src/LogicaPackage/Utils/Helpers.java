package LogicaPackage.Utils;

import LogicaPackage.Aula;
import LogicaPackage.Prenotazione;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public final class Helpers {
    private Helpers() {
        throw new UnsupportedOperationException("Questa è una classe di utility e non può essere istanziata.");
    }

    public static ArrayList<LocalTime> calcolaOrariInizioDisponibili(ArrayList<Prenotazione> prenotazioni, Aula selectedAula, LocalDate selectedDate){
        ArrayList<LocalTime> orariOccupati = new ArrayList<>();
        for (Prenotazione prenotazione : prenotazioni) {
            if (prenotazione.getData().isEqual(selectedDate) && prenotazione.getAula().getNumeroAula() == selectedAula.getNumeroAula()) {
                LocalTime orarioInizio = prenotazione.getOraInizio();
                LocalTime orarioFine = prenotazione.getOraFine();

                if(prenotazione.getAula().getTipologia().equals(Costanti.LABORATORIO)) orarioInizio = orarioInizio.minusHours(1); // Tengo l'ora prima non selezionabile come inizio (sono prenotabili a intervalli di 2 ore)

                while (!orarioInizio.equals(orarioFine)) {
                    orariOccupati.add(orarioInizio);
                    orarioInizio = orarioInizio.plusHours(1);
                }
            }
        }
        orariOccupati.add(LocalTime.of(18,0)); // Non posso prenotare alle 18. Lo segno come occupato

        ArrayList<LocalTime> orariInizioDisponibili = new ArrayList<>(Costanti.ORARI_AMMESSI);
        orariInizioDisponibili.removeAll(orariOccupati);

        return orariInizioDisponibili;
    }

    public static ArrayList<LocalTime> calcolaOrariFineDisponibili(ArrayList<Prenotazione> prenotazioni, Aula selectedAula, LocalDate selectedDate, LocalTime selectedOraInizio) {
        int hoursInterval = selectedAula.getTipologia().equals(Costanti.DIDATTICA) ? 1 : 2;
        int maxDuration = selectedAula.getTipologia().equals(Costanti.DIDATTICA) ? 8 : 4;

        LocalTime maxOrarioFine = LocalTime.of(19, 0); // Imposto 19:00 perchè userò isBefore
        for (Prenotazione prenotazione : prenotazioni) {
            if (prenotazione.getData().isEqual(selectedDate) && prenotazione.getAula().getNumeroAula() == selectedAula.getNumeroAula()) {
                LocalTime orarioPossibileFine = prenotazione.getOraInizio().plusHours(1); // Aggiungo un'ora perchè usero isBefore
                if (orarioPossibileFine.isAfter(selectedOraInizio) && orarioPossibileFine.isBefore(maxOrarioFine)) // Aggiungo un'ora perchè userò isBefore
                    maxOrarioFine = orarioPossibileFine;
            }
        }

        ArrayList<LocalTime> orariFineDisponibili = new ArrayList<>();
        LocalTime currentOrario = LocalTime.from(selectedOraInizio).plusHours(hoursInterval);
        // Scelgo se utilizzare come maxOrarioFine la durata massima oppure se ricado prima in una prenotazione (o esco fuori dal range)
        if(selectedOraInizio.plusHours(maxDuration + 1).isBefore(maxOrarioFine) && selectedOraInizio.plusHours(maxDuration + 1).isAfter(LocalTime.of(8,0)))
            maxOrarioFine = selectedOraInizio.plusHours(maxDuration + 1);
        while(currentOrario.isBefore(maxOrarioFine)){
            orariFineDisponibili.add(currentOrario);
            currentOrario = currentOrario.plusHours(hoursInterval);
        }

        return orariFineDisponibili;
    }

    public static boolean isNomeValido(String selectedNome) {
        return !selectedNome.isEmpty() && selectedNome.matches("[a-zA-Z\\s]+");
    }
}
