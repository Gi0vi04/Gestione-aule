package LogicaPackage;

import LogicaPackage.Utils.Costanti;

/**
 * Classe destinata a estendere una semplice aula in un laboratorio
 */
public class Laboratorio extends Aula {
    /**
     * Variabile che specifica o meno la presenza dei pc
     */
    private final boolean isPcPresente;
    /**
     * Variabile che specifica o meno la presenza delle prese elettriche
     */
    private final boolean isPreseElettrichePresente;

    public Laboratorio(int numeroAula, String nomeAula, int capienza, boolean isPcPresente, boolean isPreseElettrichePresente) {
        super(numeroAula, nomeAula, Costanti.LABORATORIO, capienza);
        this.isPcPresente = isPcPresente;
        this.isPreseElettrichePresente = isPreseElettrichePresente;
    }

    /**
     * Funzione che ritorna la presenza o meno di computer e prese elettriche sotto forma di stringa
     * @return stringa con i dettagli
     */
    @Override
    public String getDettagli() {
        return  "PC [" + (isPcPresente ? "✓" : "✗") + "], Prese elettriche [" + (isPreseElettrichePresente ? "✓" : "✗") + "]";
    }
    /**
     * Ritorna il numero massimo di ore prenotabili consecutivamente
     * @return numero massimo di ore prenotabili consecutivamente
     */
    @Override
    public int getMaxHours() {
        return 4;
    }
    /**
     * Ritorna l'intervallo di ore prenotabili
     * @return intervallo di ore prenotabili
     */
    @Override
    public int getHoursStep() {
        return 2;
    }
}
