package LogicaPackage;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Prenotazione implements Serializable {
    private Aula aula;
    private LocalDate data;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private String nomePrenotante;
    private String motivazionePrenotazione;

    public Prenotazione(Aula aula, LocalDate data, LocalTime oraInizio, LocalTime oraFine, String nomePrenotante, String motivazionePrenotazione){
        this.aula = aula;
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.nomePrenotante = nomePrenotante;
        this.motivazionePrenotazione = motivazionePrenotazione;
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

    public String getNomePrenotante() {
        return nomePrenotante;
    }

    public void setNomePrenotante(String nomePrenotante) {
        this.nomePrenotante = nomePrenotante;
    }

    public String getMotivazionePrenotazione() {
        return motivazionePrenotazione;
    }

    public void setMotivazionePrenotazione(String motivazionePrenotazione) {
        this.motivazionePrenotazione = motivazionePrenotazione;
    }

    @Override
    public String toString(){
        return getNomePrenotante() + " - " + getMotivazionePrenotazione();
    }

    public Aula getAula() {
        return aula;
    }

    public void setCodiceAula(Aula aula) {
        this.aula = aula;
    }
}
