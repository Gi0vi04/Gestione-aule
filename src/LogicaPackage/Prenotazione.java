package LogicaPackage;

import java.util.Date;

public class Prenotazione {
    private Date data;
    private Date oraInizio;
    private Date oraFine;
    private String nomePrenotante;
    private String motivazionePrenotazione;

    public Prenotazione(Date data, Date oraInizio, Date oraFine, String nomePrenotante, String motivazionePrenotazione){
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.nomePrenotante = nomePrenotante;
        this.motivazionePrenotazione = motivazionePrenotazione;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Date oraInizio) {
        this.oraInizio = oraInizio;
    }

    public Date getOraFine() {
        return oraFine;
    }

    public void setOraFine(Date oraFine) {
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
}
