package LogicaPackage;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe destinata a realizzare l'istanza di una prenotazione
 */
public class Prenotazione implements Serializable {
    /**
     * Variabile che contiene il nome della prenotazione
     */
    private final String nome;
    /**
     * Variabile che contiene l'aula prenotata
     */
    private final Aula aula;
    /**
     * Variabile che contiene la motivazione della prenotazione
     */
    private final String motivazione;
    /**
     * Variabile che contiene la data della prenotazione
     */
    private final LocalDate data;
    /**
     * Variabile che contiene l'ora di inizio della prenotazione
     */
    private final LocalTime oraInizio;
    /**
     * Variabile che contiene l'ora di fine della prenotazione
     */
    private final LocalTime oraFine;

    public Prenotazione(String nome, Aula aula, String motivazione, LocalDate data, LocalTime oraInizio, LocalTime oraFine){
        this.nome = nome;
        this.aula = aula;
        this.motivazione = motivazione;
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    /**
     * Getter del nome della prenotazione
     * @return nome della prenotazione
     */
    public String getNome() {
        return nome;
    }
    /**
     * Getter dell'aula prenotata
     * @return aula prenotata
     */
    public Aula getAula() {
        return aula;
    }
    /**
     * Getter della motivazione della prenotazione
     * @return motivazione della prenotazione
     */
    public String getMotivazione() {
        return motivazione;
    }
    /**
     * Getter della data di prenotazione
     * @return data di prenotazione
     */
    public LocalDate getData() {
        return data;
    }
    /**
     * Getter dell'ora di inizio della prenotazione
     * @return ora di inizio della prenotazione
     */
    public LocalTime getOraInizio() {
        return oraInizio;
    }
    /**
     * Getter dell'ora di fine della prenotazione
     * @return ora di fine della prenotazione
     */
    public LocalTime getOraFine() {
        return oraFine;
    }

    @Override
    public String toString(){
        return getNome() + " - " + getMotivazione();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Se sono lo stesso oggetto
        if (obj == null || getClass() != obj.getClass()) return false; // Se l'oggetto è null o di tipo diverso

        Prenotazione that = (Prenotazione) obj;

        // Confronto dei campi
        return (nome != null ? nome.equals(that.nome) : that.nome == null) &&
                (motivazione != null ? motivazione.equals(that.motivazione) : that.motivazione == null) &&
                (aula != null ? aula.equals(that.aula) : that.aula == null) &&
                (data != null ? data.equals(that.data) : that.data == null) &&
                (oraInizio != null ? oraInizio.equals(that.oraInizio) : that.oraInizio == null) &&
                (oraFine != null ? oraFine.equals(that.oraFine) : that.oraFine == null);
    }
}
