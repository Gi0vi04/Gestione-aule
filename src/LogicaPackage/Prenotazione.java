package LogicaPackage;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Prenotazione implements Serializable {
    private String codicePrenotazione;
    private String nome;
    private String motivazione;
    private Aula aula;
    private LocalDate data;
    private LocalTime oraInizio;
    private LocalTime oraFine;

    public Prenotazione(Aula aula, LocalDate data, LocalTime oraInizio, LocalTime oraFine, String nome, String motivazione){
        this.codicePrenotazione = aula.hashCode() + "_" + data.hashCode();
        this.aula = aula;
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.nome = nome;
        this.motivazione = motivazione;
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
    public String getNome() {
        return nome;
    }
    public String getMotivazione() {
        return motivazione;
    }
    public Aula getAula() {
        return aula;
    }
    public String getCodicePrenotazione() {
        return codicePrenotazione;
    }

    public String generateCodicePrenotazione(Prenotazione prenotazione){
        return prenotazione.getAula().hashCode() + "_" + prenotazione.data.hashCode();
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
