package LogicaPackage;

import LogicaPackage.Utils.Costanti;

import java.io.Serializable;

/**
 * Classe destinata a estendere una semplice aula in un'aula didattica
 */
public class Didattica extends Aula implements Serializable {
    /**
     * Variabile che specifica o meno la presenza della lavagna
     */
    private final boolean isLavagnaPresente;
    /**
     * Variabile che specifica o meno la presenza del videoproiettore
     */
    private final boolean isVideoproiettorePresente;

    public Didattica(int numeroAula, String nomeAula, int capienza, boolean isLavagnaPresente, boolean isVideoproiettorePresente) {
        super(numeroAula, nomeAula, Costanti.DIDATTICA, capienza);
        this.isLavagnaPresente = isLavagnaPresente;
        this.isVideoproiettorePresente = isVideoproiettorePresente;
    }

    /**
     * Funzione che ritorna la presenza o meno di lavagna e videoproiettore sotto forma di stringa
     * @return stringa con i dettagli
     */
    @Override
    public String getDettagli() {
        return  "Lavagna [" + (isLavagnaPresente ? "✓" : "✗") + "], Videoproiettore [" + (isVideoproiettorePresente ? "✓" : "✗") + "]";
    }
    /**
     * Ritorna il numero massimo di ore prenotabili consecutivamente
     * @return numero massimo di ore prenotabili consecutivamente
     */
    @Override
    public int getMaxHours() {
        return 8;
    }
    /**
     * Ritorna l'intervallo di ore prenotabili
     * @return intervallo di ore prenotabili
     */
    @Override
    public int getHoursStep() {
        return 1;
    }
}
