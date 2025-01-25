package LogicaPackage;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Prenotazione implements Serializable {
    private int codicePrenotazione;
    private String nome;
    private String motivazione;
    private Aula aula;
    private LocalDate data;
    private LocalTime oraInizio;
    private LocalTime oraFine;

    public Prenotazione(int codicePrenotazione, Aula aula, LocalDate data, LocalTime oraInizio, LocalTime oraFine, String nome, String motivazione){
        this.codicePrenotazione = codicePrenotazione;
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

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    public LocalTime getOraFine() {
        return oraFine;
    }

    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    @Override
    public String toString(){
        return getNome() + " - " + getMotivazione();
    }

    public Aula getAula() {
        return aula;
    }

    public void setCodiceAula(Aula aula) {
        this.aula = aula;
    }

    public int getCodicePrenotazione() {
        return codicePrenotazione;
    }

    public void setCodicePrenotazione(int codicePrenotazione) {
        this.codicePrenotazione = codicePrenotazione;
    }
}
