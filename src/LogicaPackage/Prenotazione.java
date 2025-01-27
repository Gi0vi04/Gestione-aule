package LogicaPackage;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Prenotazione implements Serializable {
    private String nome;
    private Aula aula;
    private String motivazione;
    private LocalDate data;
    private LocalTime oraInizio;
    private LocalTime oraFine;

    /**
     *
     * @param nome nome del prenotante
     * @param aula aula prenotata
     * @param motivazione motivazione della prenotazione
     * @param data data della prenotazione
     * @param oraInizio orario di inizio della prenotazione
     * @param oraFine orario di fine della prenotazione
     */
    public Prenotazione(String nome, Aula aula, String motivazione, LocalDate data, LocalTime oraInizio, LocalTime oraFine){
        this.nome = nome;
        this.aula = aula;
        this.motivazione = motivazione;
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public String getNome() {
        return nome;
    }
    public Aula getAula() {
        return aula;
    }
    public String getMotivazione() {
        return motivazione;
    }
    public LocalDate getData() {
        return data;
    }
    public LocalTime getOraInizio() {
        return oraInizio;
    }
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
