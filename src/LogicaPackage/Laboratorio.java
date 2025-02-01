package LogicaPackage;

import LogicaPackage.Utils.Costanti;

/**
 * Classe destinata a estendere una semplice aula in un laboratorio
 */
public class Laboratorio extends Aula {
    /**
     * Variabile che specifica o meno la presenza dei pc
     */
    private boolean isPcPresente;
    /**
     * Variabile che specifica o meno la presenza delle prese elettriche
     */
    private boolean isPreseElettrichePresente;

    public Laboratorio(int numeroAula, String nomeAula, int capienza, boolean isPcPresente, boolean isPreseElettrichePresente) {
        super(numeroAula, nomeAula, Costanti.LABORATORIO, capienza);
        this.isPcPresente = isPcPresente;
        this.isPreseElettrichePresente = isPreseElettrichePresente;
    }

    /**
     * Getter della presenza dei pc
     * @return presenza dei pc
     */
    public boolean isPcPresente() {
        return isPcPresente;
    }
    /**
     * Getter della presenza delle prese elettriche
     * @return presenza delle prese elettriche
     */
    public boolean isPreseElettrichePresente() {
        return isPreseElettrichePresente;
    }

    /**
     * Setter della presenza dei pc
     * @param pcPresente valore di presenza dei pc
     */
    public void setPcPresente(boolean pcPresente) {
        this.isPcPresente = pcPresente;
    }
    /**
     * Setter della presenza delle prese elettriche
     * @param preseElettrichePresente valore di presenza delle prese elettriche
     */
    public void setPreseElettrichePresente(boolean preseElettrichePresente) {
        this.isPreseElettrichePresente = preseElettrichePresente;
    }

    /**
     * Funzione che ritorna la presenza o meno di computer e prese elettriche sotto forma di stringa
     * @return stringa con i dettagli
     */
    @Override
    public String getDettagli() {
        return  "Computer [" + (isPcPresente ? "✓" : "✗") + "], Prese elettriche [" + (isPreseElettrichePresente ? "✓" : "✗") + "]";
    }
}
