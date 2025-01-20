package LogicaPackage;

import java.time.LocalDate;
import java.util.Date;

public class Prenotazione {
    private int codiceAula;
    private LocalDate data;
    private int oraInizio;
    private int oraFine;
    private String nomePrenotante;
    private String motivazionePrenotazione;

    public Prenotazione(int codiceAula, LocalDate data, int oraInizio, int oraFine, String nomePrenotante, String motivazionePrenotazione){
        this.codiceAula = codiceAula;
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

    public int getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(int oraInizio) {
        this.oraInizio = oraInizio;
    }

    public int getOraFine() {
        return oraFine;
    }

    public void setOraFine(int oraFine) {
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
        return "Nome prenotante: " + getNomePrenotante() +
                "\nData: " + getData().toString() +
                "\nOra inizio: " + getOraInizio() + " Ora fine: " + getOraFine() +
                "\nMotivazione: " + getMotivazionePrenotazione();
    }

    public int getCodiceAula() {
        return codiceAula;
    }

    public void setCodiceAula(int codiceAula) {
        this.codiceAula = codiceAula;
    }
}
